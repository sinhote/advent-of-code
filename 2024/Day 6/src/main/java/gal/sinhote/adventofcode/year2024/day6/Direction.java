package gal.sinhote.adventofcode.year2024.day6;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public enum Direction {
	UP("^") {
		@Override
		Direction turnRight() {
			return RIGHT;
		}

		@Override
		Position advance(Position position) {
			return new Position(position.x, position.y - 1);
		}
	}, RIGHT(">") {
		@Override
		Direction turnRight() {
			return DOWN;
		}

		@Override
		Position advance(Position position) {
			return new Position(position.x + 1, position.y);
		}
	}, DOWN("v") {
		@Override
		Direction turnRight() {
			return LEFT;
		}

		@Override
		Position advance(Position position) {
			return new Position(position.x, position.y + 1);
		}
	}, LEFT("<") {
		@Override
		Direction turnRight() {
			return UP;
		}

		@Override
		Position advance(Position position) {
			return new Position(position.x - 1, position.y);
		}
	};

	private static final Map<String, Direction> STRING_TO_DIRECTION = Stream.of(Direction.values()).collect(collectingAndThen(toMap(Direction::asString, identity()), Collections::unmodifiableMap));

	public static Optional<Direction> fromString(final String directionAsString) {
		return Optional.ofNullable(STRING_TO_DIRECTION.get(directionAsString));
	}

	private final String asString;

	Direction(final String asString) {
		this.asString = asString;
	}

	public String asString() {
		return this.asString;
	}

	abstract Direction turnRight();
	abstract Position advance(final Position position);
}
