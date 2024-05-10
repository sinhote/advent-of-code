package gal.sinhote.adventofcode._2021.day5;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class SegmentIntersections {

	private List<IntegerSegment2D> allSegments;
	private List<IntegerSegment2D> nonObliqueSegments;
	private Set<IntegerPoint2D> allSegmentIntersections;
	private Set<IntegerPoint2D> nonObliqueSegmentIntersections;

	public SegmentIntersections(Path filePath) throws IOException {
		allSegments = readSegmentsFromFile(filePath);

		nonObliqueSegments = allSegments.stream().filter(segment -> !segment.isOblique()).collect(toList());

		allSegmentIntersections = calculateIntersections(allSegments);
		nonObliqueSegmentIntersections = calculateIntersections(nonObliqueSegments);
	}

	private static List<IntegerSegment2D> readSegmentsFromFile(Path filePath) throws IOException {
		try (BufferedReader br = Files.newBufferedReader(filePath)) {
			return br.lines().map(IntegerSegment2D::parseSegment2D).collect(toList());
		}
	}

	private static <T extends IntegerSegment2D> Set<IntegerPoint2D> calculateIntersections(List<T> segments) {
		Set<IntegerPoint2D> retVal = new HashSet<>();

		for (int i = 0; i < segments.size() - 1; i++) {
			IntegerSegment2D current = segments.get(i);
			ListIterator<T> it = segments.listIterator(i + 1);
			while (it.hasNext()) {
				IntegerSegment2D other = it.next();

				retVal.addAll(current.getIntersection(other));
			}
		}

		return retVal;
	}

	public List<IntegerSegment2D> getAllSegments() {
		return Collections.unmodifiableList(allSegments);
	}

	public List<IntegerSegment2D> getNonObliqueSegments() {
		return Collections.unmodifiableList(nonObliqueSegments);
	}

	public Set<IntegerPoint2D> getAllSegmentIntersectionPoints() {
		return Collections.unmodifiableSet(allSegmentIntersections);
	}

	public Set<IntegerPoint2D> getNonObliqueSegmentIntersectionPoints() {
		return Collections.unmodifiableSet(nonObliqueSegmentIntersections);
	}

}
