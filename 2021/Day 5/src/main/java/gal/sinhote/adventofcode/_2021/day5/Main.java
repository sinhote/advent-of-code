package gal.sinhote.adventofcode._2021.day5;

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
				SegmentIntersections intersections = new SegmentIntersections(
						Paths.get(inputFileURL.toURI()));

				System.out.println(String.format("There are %d points where two or more vertical or horizontal lines overlap", intersections.getNonObliqueSegmentIntersectionPoints().size()));
				System.out.println(String.format("There are %d points where two or more lines overlap", intersections.getAllSegmentIntersectionPoints().size()));

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
