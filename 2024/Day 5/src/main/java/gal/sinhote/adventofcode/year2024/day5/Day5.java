package gal.sinhote.adventofcode.year2024.day5;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Day5 {

	public static void main(String[] argv) {
		if (argv.length < 1) {
			System.err.println("An input file must be provided!");
			return;
		}

		final RuleBasedPageComparator compare = new RuleBasedPageComparator();
		try(BufferedReader br = Files.newBufferedReader(Paths.get(argv[0]))) {
			// Process rules first
			for (String line = br.readLine(); line != null && !line.isEmpty(); line = br.readLine()) {
				String[] pages = line.split("\\s*\\|\\s*");
				if (pages.length != 2) {
					throw new IllegalArgumentException("Expected two integers separated by a pipe (\"|\"), but instead got: " + line);
				}
				compare.addOrderingRule(Integer.parseInt(pages[0]), Integer.parseInt(pages[1]));
			}

			final List<List<Integer>> updates = br.lines()
					.map(line -> Stream.of(line.split(",")).map(Integer::valueOf).collect(toList()))
					.collect(toList());

			//			for(final List<Integer> update : updates) {
			//				System.out.println(update.stream().map(Object::toString).collect(Collectors.joining(", ")));
			//			}

			// Part 1
			final long sumMiddleElementsInSortedUpdates = updates.stream()
					.filter(compare::isSorted)
					.map(list -> (long)list.get(list.size()/2))
					.reduce((long1, long2) -> long1+long2)
					.orElse(0L);

			System.out.println("The sum of the middle pages in ordered safety manual page updates is " + sumMiddleElementsInSortedUpdates);

			// Part 2
			final long sumMiddleElementsInUnsortedUpdatesAfterSorting = updates.stream()
					.filter(update -> !compare.isSorted(update))
					.map(update -> {
						Collections.sort(update, compare); return update;
					})
					.map(update -> (long)update.get(update.size()/2))
					.reduce((long1, long2) -> long1+long2)
					.orElse(0L);

			System.out.println("The sum of the middle pages in unordered safety manual page updates after being correctly ordered is " + sumMiddleElementsInUnsortedUpdatesAfterSorting);

		} catch (IOException e) {
			System.err.println("Couldn't read input file " + argv[0]);
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Found unparseable integer in input file", nfe);
		}
	}
}