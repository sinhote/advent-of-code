package gal.sinhote.adventofcode.year2024.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 {

	public static void main(String[] argv) {
		if (argv.length < 1) {
			System.err.println("An input file must be provided!");
			return;
		}

		try {
			final List<String> searchField = Files.lines(Paths.get(argv[0])).collect(Collectors.toList());

			// First part
			final XmasCounter xmasCounter = new XmasCounter(searchField);
			System.out.println("There are " + xmasCounter.getNumberOfXmas() + " XMAS occurences");

			// Second part
			final CrossMasCounter crossMasCounter = new CrossMasCounter(searchField);
			System.out.println("There are " + crossMasCounter.getNumberOfXmas() + " X-MAS occurences");

		} catch (IOException e) {
			System.err.println("Couldn't read input file " + argv[0]);
		}
	}
}