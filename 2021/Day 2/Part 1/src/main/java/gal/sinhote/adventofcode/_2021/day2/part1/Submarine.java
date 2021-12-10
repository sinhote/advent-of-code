package gal.sinhote.adventofcode._2021.day2.part1;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Tracks position of a submarine according to give {@link Movement}s
 */
public class Submarine {

	private int horizontalPosition;
	private int depth;

	public Submarine() {
		horizontalPosition = 0;
		depth = 0;
	}

	public void move(Maneuver maneuver) {
		Objects.requireNonNull(maneuver);

		switch (maneuver.getMovement()) {
		case FORWARD:
			horizontalPosition += maneuver.getAmount();
			break;
		case UP:
			depth -= maneuver.getAmount();
			break;
		case DOWN:
			depth += maneuver.getAmount();
			break;
		}
	}

	public void move(Stream<? extends Maneuver> maneuvers) {
		maneuvers.forEachOrdered(this::move);
	}

	public void move(Collection<? extends Maneuver> maneuvers) {
		for(Maneuver maneuver : maneuvers) {
			move(maneuver);
		}
	}

	public int[] getCoordinates() {
		return new int[] {horizontalPosition, depth};
	}

	public int getHorizontalPosition() {
		return horizontalPosition;
	}

	public int getDepth() {
		return depth;
	}
}
