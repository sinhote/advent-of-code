package gal.sinhote.adventofcode._2021.day1.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {

		if (args.length == 0) {
			System.err.println("Expected a parameter containing an input file name");
			return;
		}

		URL inputFileURL = Main.class.getResource(args[0]);

		if (inputFileURL == null) {
			System.err.println("Resource could not be found in classpath: " + args[0]);
		} else {
			try {
				Path filePath = Paths.get(inputFileURL.toURI());

				System.out.println("Number of depth values that are bigger than the previous value: "
						+ countIncreasingDepthMeasurements(filePath));

			} catch (URISyntaxException use) {
				System.err.println("Malformed input file URI: " + use.getMessage());
			} catch (IOException ioe) {
				System.err.println("Error reading input file: " + ioe.getMessage());
			} catch (NumberFormatException nfe) {
				System.err.println("Input file is expected to contain lines with integer numbers only. Error: "
						+ nfe.getMessage());
			}

		}
	}

	public static long countIncreasingDepthMeasurements(Path inputFile) throws IOException, NumberFormatException {
		try (BufferedReader br = Files.newBufferedReader(inputFile)) {
			IncreasingValueCounter<Integer> valueCounter = new IncreasingValueCounter<>(Integer.parseInt(br.readLine()));

			valueCounter.nextElements(br.lines().map(Integer::parseInt));

			return valueCounter.getCount();
		}
	}

}
