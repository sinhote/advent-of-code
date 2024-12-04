package gal.sinhote.adventofcode.year2024.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day3 {


	public static void main(String[] argv) {
		if (argv.length < 1) {
			System.err.println("An input file must be provided!");
			return;
		}

		try {
			final MulFinder mulAccumulator = new MulFinder();
			mulAccumulator.addLines(Files.lines(Paths.get(argv[0])));
			System.out.println("The sum is: " + mulAccumulator.getSum());

			final MulFinderWithEnablement enabledAcc = new MulFinderWithEnablement();
			enabledAcc.addLines(Files.lines(Paths.get(argv[0])));
			System.out.println("The sum with enablement is: " + enabledAcc.getSum());
		} catch (IOException e) {
			System.err.println("Couldn't read input file " + argv[0]);
		}
	}
}