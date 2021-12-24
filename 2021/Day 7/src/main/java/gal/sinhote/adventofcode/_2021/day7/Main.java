package gal.sinhote.adventofcode._2021.day7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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
				FuelSaver saver = FuelSaver.fromFile(
						Paths.get(inputFileURL.toURI()));

				System.out.println(String.format("Optimal fuel consumption for first part is %d", saver.getOptimalConsumption()));
				System.out.println(String.format("Optimal fuel consumption for second part is %d", saver.getOptimalConsumptionPart2()));

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
