package gal.sinhote.adventofcode.year2024.day1;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.IntStream;

public class Day1 {

	public static void main(String[] argv) {
		if (argv.length < 1) {
			System.err.println("An input file must be provided!");
			return;
		}

		try {
			final ListParser.ParsedLists parsedLists = new ListParser().parseLists(argv[0]);

			// Part 1
			System.out.println("The distance is " + part1(parsedLists));

			// Part 2
			System.out.println("The similarity is " + part2(parsedLists));
		} catch (IOException e) {
			System.err.println("Error parsing input file: " + e.getLocalizedMessage());
		}
	}

	private static int part1(final ListParser.ParsedLists parsedLists) {
		Collections.sort(parsedLists.leftList);
		Collections.sort(parsedLists.rightList);

		return IntStream.range(0, parsedLists.leftList.size())
				.map(i -> Math.abs(parsedLists.leftList.get(i) - parsedLists.rightList.get(i)))
				.sum();

	}

	private static long part2(final ListParser.ParsedLists parsedLists) {
		final Map<Integer, Long> leftListFrequencies =
				parsedLists.leftList.stream().collect(groupingBy(identity(), counting()));
		final Map<Integer, Long> rightListFrequencies =
				parsedLists.rightList.stream().collect(groupingBy(identity(), counting()));

		return leftListFrequencies.entrySet().stream()
				.filter(leftEntry -> rightListFrequencies.containsKey(leftEntry.getKey()))
				.collect(
						summingLong(
								leftEntry -> leftEntry.getKey() * leftEntry.getValue() * rightListFrequencies.get(leftEntry.getKey())));
	}

}