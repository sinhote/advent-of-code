package gal.sinhote.adventofcode.year2024.day6;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class GuardBearing {
	private final Position position;
	private final Direction direction;

	public GuardBearing(final int x, final int y, final Direction direction) {
		this.position = new Position(x, y);
		this.direction = requireNonNull(direction);
	}

	public GuardBearing(final Position position, final Direction direction) {
		this.position = requireNonNull(position);
		this.direction = requireNonNull(direction);
	}

	public int x() {
		return position.x;
	}

	public int y() {
		return position.y;
	}

	public Position position() {
		return position;
	}

	public GuardBearing advance() {
		return new GuardBearing(direction.advance(position), direction);
	}

	public GuardBearing turnRight() {
		return new GuardBearing(position, direction.turnRight());
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof GuardBearing && position.equals(((GuardBearing) o).position) && direction.equals(((GuardBearing) o).direction);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, direction);
	}

	@Override
	public String toString() {
		return direction.asString() + position.toString();
	}
}
