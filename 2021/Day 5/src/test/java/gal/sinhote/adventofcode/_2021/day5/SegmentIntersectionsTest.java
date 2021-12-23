package gal.sinhote.adventofcode._2021.day5;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class SegmentIntersectionsTest {

	@Test
	void testRealInput() throws IOException, URISyntaxException {
		Path path = Paths.get(SegmentIntersectionsTest.class.getResource("/testInput.txt").toURI());

		SegmentIntersections intersections = new SegmentIntersections(path);

		assertThat(intersections.getAllSegments(), hasSize(10));
		assertThat(intersections.getNonObliqueSegments(), hasSize(6));
		assertThat(intersections.getNonObliqueSegmentIntersectionPoints(), hasSize(5));
		assertThat(intersections.getAllSegmentIntersectionPoints(), hasSize(12));
	}

}
