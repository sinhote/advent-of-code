package gal.sinhote.adventofcode._2021.day6;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class LanternfishSimulatorTest {

	@Test
	void testRealInput() throws IOException, URISyntaxException {
		Path path = Paths.get(LanternfishSimulatorTest.class.getResource("/testInput.txt").toURI());

		LanternfishSimulator simulator = LanternfishSimulator.fromFile(path);

		assertThat(simulator.getFishDaysToSpawn(), contains(3, 4, 3, 1, 2));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(), contains(2, 3, 2, 0, 1));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(), contains(1, 2, 1, 6, 0, 8));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(), contains(0, 1, 0, 5, 6, 7, 8));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(), contains(6, 0, 6, 4, 5, 6, 7, 8, 8));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(), contains(5, 6, 5, 3, 4, 5, 6, 7, 7, 8));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(), contains(4, 5, 4, 2, 3, 4, 5, 6, 6, 7));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(), contains(3, 4, 3, 1, 2, 3, 4, 5, 5, 6));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(), contains(2, 3, 2, 0, 1, 2, 3, 4, 4, 5));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(), contains(1, 2, 1, 6, 0, 1, 2, 3, 3, 4, 8));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(), contains(0, 1, 0, 5, 6, 0, 1, 2, 2, 3, 7, 8));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(), contains(6, 0, 6, 4, 5, 6, 0, 1, 1, 2, 6, 7, 8, 8, 8));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(), contains(5, 6, 5, 3, 4, 5, 6, 0, 0, 1, 5, 6, 7, 7, 7, 8, 8));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(), contains(4, 5, 4, 2, 3, 4, 5, 6, 6, 0, 4, 5, 6, 6, 6, 7, 7, 8, 8));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(),
				contains(3, 4, 3, 1, 2, 3, 4, 5, 5, 6, 3, 4, 5, 5, 5, 6, 6, 7, 7, 8));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(),
				contains(2, 3, 2, 0, 1, 2, 3, 4, 4, 5, 2, 3, 4, 4, 4, 5, 5, 6, 6, 7));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(),
				contains(1, 2, 1, 6, 0, 1, 2, 3, 3, 4, 1, 2, 3, 3, 3, 4, 4, 5, 5, 6, 8));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(),
				contains(0, 1, 0, 5, 6, 0, 1, 2, 2, 3, 0, 1, 2, 2, 2, 3, 3, 4, 4, 5, 7, 8));
		simulator.newDay();
		assertThat(simulator.getFishDaysToSpawn(),
				contains(6, 0, 6, 4, 5, 6, 0, 1, 1, 2, 6, 0, 1, 1, 1, 2, 2, 3, 3, 4, 6, 7, 8, 8, 8, 8));

		// After 80 days
		simulator.newDays(62);
		assertThat(simulator.getNumberOfFish(), is(5934));
	}

}
