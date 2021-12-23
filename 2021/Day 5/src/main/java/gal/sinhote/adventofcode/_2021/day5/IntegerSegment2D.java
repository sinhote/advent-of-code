package gal.sinhote.adventofcode._2021.day5;

import static java.lang.Math.abs;
import static java.lang.String.format;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class IntegerSegment2D {

	SortedSet<IntegerPoint2D> segmentPoints;
	private final IntegerPoint2D startPoint;

	public IntegerSegment2D(IntegerPoint2D startPoint, IntegerPoint2D endPoint) {
		Objects.requireNonNull(startPoint);
		Objects.requireNonNull(endPoint);

		// Need to store this separately because it is used to properly order the set
		this.startPoint = startPoint;

		segmentPoints = calculateAllSegmentPoints(startPoint, endPoint);
	}

	/**
	 * This is to use with the SortedSet and return points ordered from startPoint
	 * to endPoint
	 *
	 * @param first
	 * @param second
	 * @return
	 */
	private int comparator(IntegerPoint2D first, IntegerPoint2D second) {
		double distanceToFirst = startPoint.distanceTo(first);
		double distanceToSecond = startPoint.distanceTo(second);

		if (distanceToFirst < distanceToSecond) {
			return -1;
		} else if (distanceToFirst > distanceToSecond) {
			return 1;
		} else {
			if (first.getX() != second.getX()) {
				return first.getX() - second.getX();
			} else if (first.getY() != second.getY()) {
				return first.getY() - second.getY();
			} else {
				return 0;
			}
		}
	}

	private SortedSet<IntegerPoint2D> calculateAllSegmentPoints(IntegerPoint2D startPoint, IntegerPoint2D endPoint) {
		// Check the start and end points describe a segment that's horizontal, vertical
		// or with a 45 degree angle
		int xDiff = abs(startPoint.getX() - endPoint.getX());
		int yDiff = abs(startPoint.getY() - endPoint.getY());

		if (xDiff != 0 && yDiff != 0 && xDiff != yDiff) {
			throw new IllegalArgumentException(
					"This class only supports supports segments that are horizontal, vertical or at a 45 degree angle");
		}

		SortedSet<IntegerPoint2D> points = new TreeSet<>(this::comparator);
		int x = startPoint.getX();
		int y = startPoint.getY();
		while (x != endPoint.getX() || y != endPoint.getY()) {
			points.add(new IntegerPoint2D(x, y));

			if (x != endPoint.getX()) {
				if (x < endPoint.getX()) {
					x++;
				} else {
					x--;
				}
			}

			if (y != endPoint.getY()) {
				if (y < endPoint.getY()) {
					y++;
				} else {
					y--;
				}
			}
		}
		points.add(endPoint);

		return points;
	}

	public static IntegerSegment2D parseSegment2D(String segmentStr) {
		try {
			String[] points = segmentStr.split("\\s*->\\s*");
			return new IntegerSegment2D(IntegerPoint2D.parsePoint2D(points[0]), IntegerPoint2D.parsePoint2D(points[1]));
		} catch (ArrayIndexOutOfBoundsException | IllegalArgumentException ex) {
			throw new IllegalArgumentException(
					format("Segment string does not have the appropriate format: %s", segmentStr), ex);
		}
	}

	public boolean isVertical() {
		return !isPunctual() && segmentPoints.first().getX() == segmentPoints.last().getX();
	}

	public boolean isHorizontal() {
		return !isPunctual() && segmentPoints.first().getY() == segmentPoints.last().getY();
	}

	public boolean isPunctual() {
		return segmentPoints.size() == 1;
	}

	public boolean isOblique() {
		return !(isVertical() || isHorizontal() || isPunctual());
	}

	public Set<IntegerPoint2D> getIntersection(IntegerSegment2D that) {
		HashSet<IntegerPoint2D> retVal = new HashSet<>(this.segmentPoints);
		retVal.retainAll(that.segmentPoints);

		return retVal;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof IntegerSegment2D) {
			IntegerSegment2D otherSegment = (IntegerSegment2D) other;
			return this.segmentPoints.equals(otherSegment.segmentPoints);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(segmentPoints);
	}

	@Override
	public String toString() {
		return format("%s -> %s", segmentPoints.first(), segmentPoints.last());
	}
}
