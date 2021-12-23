package gal.sinhote.adventofcode._2021.day5;

import static java.lang.Math.sqrt;
import static java.lang.String.format;

import java.util.Objects;

public class IntegerPoint2D {
	private int x;
	private int y;

	public static final IntegerPoint2D ORIGIN = new IntegerPoint2D(0,0);

	public IntegerPoint2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static IntegerPoint2D parsePoint2D(String pointStr) {
		try {
			String[] coordinates = pointStr.split("\\s*,\\s*");
			return new IntegerPoint2D(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
			throw new IllegalArgumentException(format("Point string does not have the appropriate format: %s", pointStr), ex);
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double distanceTo(IntegerPoint2D that) {
		Objects.requireNonNull(that);

		int xDiff = this.x - that.x;
		int yDiff = this.y - that.y;

		return sqrt(xDiff*xDiff + yDiff*yDiff);
	}

	public double radius() {
		return distanceTo(ORIGIN);
	}

	@Override
	public String toString() {
		return format("(%d, %d)", x, y);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof IntegerPoint2D) {
			IntegerPoint2D otherPoint = (IntegerPoint2D) other;
			return this.x == otherPoint.x && this.y == otherPoint.y;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
