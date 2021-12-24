package gal.sinhote.adventofcode._2021.day6;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LanternfishSimulator {

	private List<Lanternfish> fish = new ArrayList<>();

	private class Lanternfish {
		public static final int NEWBORN_DAYS_TO_SPAWN = 8;
		public static final int RELOAD_DAYS_TO_SPAWN = 6;

		private int daysToSpawn;

		public Lanternfish() {
			daysToSpawn = NEWBORN_DAYS_TO_SPAWN;
		}

		public Lanternfish(int daysToSpawn) {
			if (daysToSpawn < 0) {
				throw new IllegalArgumentException("Days to spawn must be positive or 0");
			}
			this.daysToSpawn = daysToSpawn;
		}

		public boolean newDay() {
			boolean newFish = false;

			if (daysToSpawn == 0) {
				daysToSpawn = RELOAD_DAYS_TO_SPAWN;
				newFish = true;
			} else {
				daysToSpawn--;
			}

			return newFish;
		}
	}

	private LanternfishSimulator(Collection<Integer> initialFish) {
		Objects.requireNonNull(initialFish);

		fish.addAll(initialFish.stream().map(Lanternfish::new).collect(toList()));

		if (fish.isEmpty()) {
			throw new IllegalArgumentException("Initial fish list must not be empty");
		}
	}

	public static LanternfishSimulator fromCollection(Collection<Integer> initialFish) {
		LanternfishSimulator retVal = new LanternfishSimulator(initialFish);
		return retVal;
	}

	public static LanternfishSimulator fromFile(Path inputFile) throws IOException {
		try(BufferedReader br = Files.newBufferedReader(inputFile)) {
			String inputLine = br.readLine();
			if (inputLine != null) {
				return new LanternfishSimulator(Stream.of(inputLine.split(",")).map(Integer::parseInt).collect(toList()));
			} else {
				throw new IllegalArgumentException("Inputfile must not be empty");
			}
		}
	}

	public void newDay() {
		List<Lanternfish> newFish = fish.stream().map(Lanternfish::newDay).filter(b -> b).map(b -> new Lanternfish()).collect(toList());
		fish.addAll(newFish);
	}

	public void newDays(int days) {
		if (days < 0) {
			throw new IllegalArgumentException("Provided number of days must be positive");
		}

		IntStream.range(0, days).forEach(i -> newDay());
	}

	public int getNumberOfFish() {
		return fish.size();
	}

	public List<Integer> getFishDaysToSpawn() {
		return fish.stream().map(f -> f.daysToSpawn).collect(toList());
	}
}
