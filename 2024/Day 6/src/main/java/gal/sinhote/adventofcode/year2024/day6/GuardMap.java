package gal.sinhote.adventofcode.year2024.day6;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import gal.sinhote.adventofcode.year2024.day6.Trajectory.StopReason;

public class GuardMap {

	private static final String GUARD_PATTERN = "[<v>^]";
	private static final char OBSTACLE = '#';

	private final List<String> theMap;
	private final Pattern guardPattern;
	private GuardBearing guardInitialBearing;

	public GuardMap() {
		theMap = new ArrayList<>();
		guardPattern = Pattern.compile(GUARD_PATTERN);
		guardInitialBearing = null;
	}

	public void addLine(final String lineToAdd) {
		requireNonNull(lineToAdd);
		final Matcher theMatcher = guardPattern.matcher(lineToAdd);
		if (theMatcher.find()) {
			final GuardBearing foundGuardBearing = new GuardBearing(
					theMatcher.start(),
					theMap.size(),
					Direction.fromString(theMatcher.group()).orElseThrow(() -> new IllegalArgumentException("Incorrect direction: " + theMatcher.group())));
			if (guardInitialBearing != null) {
				throw new IllegalArgumentException("Added new line with guard at " + guardInitialBearing + ", but a guard was already found at " + foundGuardBearing);
			}
			this.guardInitialBearing = foundGuardBearing;
		}
		theMap.add(lineToAdd);
	}

	public List<GuardBearing> getTrajectory() {
		return getTrajectory(guardInitialBearing).steps;
	}

	public Set<Position> findLoops(final List<GuardBearing> steps) {
		final Set<Position> obstaclesCausingLoops = new HashSet<>();
		for (final GuardBearing bearing : steps) {
			final Position obstaclePosition = bearing.advance().position();
			if (!isOutOfBounds(obstaclePosition) && !isObstacle(obstaclePosition)) {
				final Trajectory trajectory = getTrajectory(steps.get(0), obstaclePosition);
				if (trajectory.stopReason == StopReason.LOOP) {
					obstaclesCausingLoops.add(obstaclePosition);
				}
			}
		}
		return obstaclesCausingLoops;
	}

	private Trajectory getTrajectory(final GuardBearing startingPoint, final Position... extraObstacleArray) {

		final Set<Position> extraObstacles = Stream.of(extraObstacleArray).collect(toSet());
		GuardBearing currentBearing = requireNonNull(startingPoint, "The guard's position is not initialized");

		final List<GuardBearing> trajectory = new ArrayList<>();
		final Set<GuardBearing> visited = new HashSet<>();
		Trajectory.StopReason stopReason = StopReason.OUT_OF_BOUNDS;

		trajectory.add(currentBearing);
		visited.add(currentBearing);

		while (true) {
			final GuardBearing nextBearing = currentBearing.advance();

			if (isOutOfBounds(nextBearing.position())) {
				break;
			}

			if (extraObstacles.contains(nextBearing.position()) || isObstacle(nextBearing.position())) {
				currentBearing = currentBearing.turnRight();
			} else {
				currentBearing = nextBearing;
			}

			if (visited.contains(currentBearing)) {
				stopReason = StopReason.LOOP;
				break;
			}

			trajectory.add(currentBearing);
			visited.add(currentBearing);
		}

		return new Trajectory(stopReason, trajectory);
	}

	private boolean isOutOfBounds(final Position position) {
		return position.x < 0 || position.y < 0 || position.y >= theMap.size() || position.x >= theMap.get(position.y).length();
	}

	private boolean isObstacle(final Position position) {
		return theMap.get(position.y).charAt(position.x) == OBSTACLE;
	}
}
