package gal.sinhote.adventofcode._2021.day6;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Lanternfish reproduction works in cycles, so this is used to our advantage in
 * this implementation.
 *
 * <p>
 * Instead of representing each individual fish, we group how many fish has X
 * days left to reproduce. We use a circular index to go through the list, the
 * current element representing those fish with age 0, that is, those who will
 * reproduce today.
 *
 * <p>
 * Because newborn fish take longer to reproduce, they have to be handled
 * separately. Because they take one full cycle plus two days to reproduce for
 * the first time, they cannot be added to the list immediately. Instead, two
 * separate integers are used. One stores those fish with age 8, the other with
 * age 7. Fish with age 6 can be added to those who have just reproduced,
 * <i>after</i> they do. This could be generalized by using a queue, which would
 * work for any combination of normal spawn rate and newborn spawn rate, but as
 * these values are constant, it is not really necessary in this case.
 *
 * <p>
 * The only disadvantage with this approach is that we cannot see a separate
 * list of fish in the order they were born with their respective "time to
 * reproduce", which was possible with the naive approach.
 *
 * @return
 */
public class EfficientLanternfishSimulator {

	public static final int NEWBORN_DAYS_TO_SPAWN = 8;
	public static final int DAYS_TO_SPAWN = 7;

	private long[] fish = new long[DAYS_TO_SPAWN];

	// Circular index
	private int today;

	private long eightDays;
	private long nineDays;

	private EfficientLanternfishSimulator(Collection<Integer> initialFish) {
		Objects.requireNonNull(initialFish);

		try {
			initialFish.stream().forEach(i -> fish[i]++);

			if (LongStream.of(fish).allMatch(f -> f == 0)) {
				throw new IllegalArgumentException("Initial fish list must not be empty");
			}
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			throw new IllegalArgumentException(
					String.format("Lanternfish days until reproduction must not be greated than %d: %s", DAYS_TO_SPAWN,
							aioobe.getMessage()),
					aioobe);
		}

		today = 0;
		eightDays = 0;
		nineDays = 0;
	}

	public static EfficientLanternfishSimulator fromCollection(Collection<Integer> initialFish) {
		EfficientLanternfishSimulator retVal = new EfficientLanternfishSimulator(initialFish);
		return retVal;
	}

	public static EfficientLanternfishSimulator fromFile(Path inputFile) throws IOException {
		try (BufferedReader br = Files.newBufferedReader(inputFile)) {
			String inputLine = br.readLine();
			if (inputLine != null) {
				return new EfficientLanternfishSimulator(
						Stream.of(inputLine.split(",")).map(Integer::parseInt).collect(toList()));
			} else {
				throw new IllegalArgumentException("Inputfile must not be null");
			}
		}
	}

	public void newDay() {
		long newBorn = fish[today];

		// Add the fish born two days ago to the fish turning 0 days to reproduce in the next cycle.
		fish[today] += eightDays;

		// Update daily values
		eightDays = nineDays;
		nineDays = newBorn;

		// Update pointer
		today = (today + 1) % fish.length;
	}

	public void newDays(int days) {
		if (days < 0) {
			throw new IllegalArgumentException("Provided number of days must be positive");
		}

		IntStream.range(0, days).forEach(i -> newDay());
	}

	public long getNumberOfFish() {
		return LongStream.of(fish).sum() + eightDays + nineDays;
	}

}
