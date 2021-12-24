package gal.sinhote.adventofcode._2021.day6;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class EfficientLanternfishSimulatorTest {

	@Test
	void testRealInput() throws IOException, URISyntaxException {
		Path path = Paths.get(EfficientLanternfishSimulatorTest.class.getResource("/testInput.txt").toURI());

		EfficientLanternfishSimulator simulator = EfficientLanternfishSimulator.fromFile(path);

		assertThat(simulator.getNumberOfFish(), is(5L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(5L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(6L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(7L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(9L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(10L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(10L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(10L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(10L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(11L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(12L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(15L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(17L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(19L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(20L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(20L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(21L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(22L));
		simulator.newDay();
		assertThat(simulator.getNumberOfFish(), is(26L));

		// After 80 days
		simulator.newDays(62);
		assertThat(simulator.getNumberOfFish(), is(5934L));

		// After 256 days
		simulator.newDays(176);
		assertThat(simulator.getNumberOfFish(), is(26984457539L));
	}

}
