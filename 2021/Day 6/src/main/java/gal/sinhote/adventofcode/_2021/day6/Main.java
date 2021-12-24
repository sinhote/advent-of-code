package gal.sinhote.adventofcode._2021.day6;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Main {

	public static final int NUMBER_OF_DAYS = 80;

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
				LanternfishSimulator simulator = LanternfishSimulator.fromFile(
						Paths.get(inputFileURL.toURI()));

				simulator.newDays(NUMBER_OF_DAYS);

				System.out.println(String.format("After %d days, there are %d lanternfish in the school", NUMBER_OF_DAYS, simulator.getNumberOfFish()));

			} catch (URISyntaxException use) {
				System.err.println("Malformed input file URI: " + use.getMessage());
			} catch (IOException ioe) {
				System.err.println("Error reading input file: " + ioe.getMessage());
			} catch (IllegalArgumentException iae) {
				System.err.println("Input file has an incorrect format: " + iae.getMessage());
			}
		}
	}
}
