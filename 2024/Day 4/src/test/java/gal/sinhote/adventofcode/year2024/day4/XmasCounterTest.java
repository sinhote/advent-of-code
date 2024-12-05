package gal.sinhote.adventofcode.year2024.day4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class XmasCounterTest {

	@Test
	void testCounter() throws IOException, URISyntaxException {
		final List<String> searchField = Files.lines(Paths.get(getClass().getResource("/test-input").toURI())).collect(Collectors.toList());
		final XmasCounter xmasCounter = new XmasCounter(searchField);
		assertThat(xmasCounter.getNumberOfXmas(), is(18L));
	}

}
