package gal.sinhote.adventofcode._2021.day2.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import gal.sinhote.adventofcode._2021.day2.part1.Maneuver;

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

				final int[] finalPosition = moveSubmarine(filePath);

				System.out.println(
						String.format("The final submarine position is (%d, %d). The product of the coordinates is: %d",
								finalPosition[0], finalPosition[1], finalPosition[0] * finalPosition[1]));

			} catch (URISyntaxException use) {
				System.err.println("Malformed input file URI: " + use.getMessage());
			} catch (IOException ioe) {
				System.err.println("Error reading input file: " + ioe.getMessage());
			} catch (IllegalArgumentException iae) {
				System.err.println("Input file contained an incorrect maneuver description: " + iae.getMessage());
			}

		}
	}

	public static int[] moveSubmarine(Path inputFile) throws IOException {
		try (BufferedReader br = Files.newBufferedReader(inputFile)) {
			SubmarineWithAim pilot = new SubmarineWithAim();

			pilot.move(br.lines().map(Maneuver::parseManeuver));

			return pilot.getCoordinates();
		}
	}

}
