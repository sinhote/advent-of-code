package gal.sinhote.adventofcode._2021.day7;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class FuelSaverTest {

	@Test
	void testRealInput() throws IOException, URISyntaxException {
		Path path = Paths.get(FuelSaverTest.class.getResource("/testInput.txt").toURI());

		FuelSaver saver = FuelSaver.fromFile(path);

		assertThat(saver.getOptimalConsumption(), is(37));
		assertThat(saver.getOptimalConsumptionPart2(), is(168));
	}

}
