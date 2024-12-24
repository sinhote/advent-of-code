package gal.sinhote.adventofcode.year2024.day6;

import java.util.Objects;

public class Position {
	public final int x;
	public final int y;

	public Position(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Position && ((Position) o).x == x && ((Position) o).y == y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
