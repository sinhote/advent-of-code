package gal.sinhote.adventofcode._2021.day1.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {

		if (args.length < 2) {
			System.err.println("Expected two arguments: an input file name and an integer indicating the size of the sliding window to use");
			return;
		}

		int windowSize;
		try {
			windowSize = Integer.parseInt(args[1]);
		} catch (NumberFormatException nfe) {
			System.err.println("Could not parse second argument as an integer: " + nfe.getMessage());
			return;
		}

		URL inputFileURL = Main.class.getResource(args[0]);

		if (inputFileURL == null) {
			System.err.println("Resource could not be found in classpath: " + args[0]);
		} else {
			try {
				Path filePath = Paths.get(inputFileURL.toURI());

				System.out.println("Number of depth window values that are bigger than the previous window value: "
						+ countIncreasingDepthMeasurements(filePath, windowSize));

			} catch (URISyntaxException use) {
				System.err.println("Malformed input file URI: " + use.getMessage());
			} catch (IOException ioe) {
				System.err.println("Error reading input file: " + ioe.getMessage());
			} catch (NumberFormatException nfe) {
				System.err.println("Input file is expected to contain lines with integer numbers only. Error: " + nfe.getMessage());
			}

		}
	}

	public static long countIncreasingDepthMeasurements(Path inputFile, int windowSize) throws IOException, NumberFormatException {
		try (BufferedReader br = Files.newBufferedReader(inputFile)) {
			IncreasingWindowValueCounter valueCounter = new IncreasingWindowValueCounter(Integer.parseInt(br.readLine()), windowSize);

			valueCounter.nextElements(br.lines().map(Integer::parseInt));

			return valueCounter.getCount();
		}
	}

}
