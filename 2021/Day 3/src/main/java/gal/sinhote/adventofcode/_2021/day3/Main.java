package gal.sinhote.adventofcode._2021.day3;

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
				DiagnosticReport report = new DiagnosticReport();

				report.addAllDiagnosticNumbers(filePath);

				System.out.println(String.format(
						"Gamma rate is %s. Epsilon rate is %s. The product of the rates is: %d.",
						report.getGammaRateAsStr(), report.getEpsilonRateAsStr(),
						report.getGammaRate() * report.getEpsilonRate()));

				System.out.println(String.format(
						"Oxygen generation rating is %s. CO2 scrubber rating is %s. The product of the rates is: %d.",
						report.getO2GeneratorRatingAsStr(), report.getCO2ScrubberRatingAsStr(),
						report.getO2GeneratorRating() * report.getCO2ScrubberRating()));

			} catch (URISyntaxException use) {
				System.err.println("Malformed input file URI: " + use.getMessage());
			} catch (IOException ioe) {
				System.err.println("Error reading input file: " + ioe.getMessage());
			} catch (IllegalArgumentException iae) {
				System.err.println("Input file contained an incorrect maneuver description: " + iae.getMessage());
			}

		}
	}

	public static void addAllReportLinesFromPath(DiagnosticReport report, Path inputFile) throws IOException {
		try (BufferedReader br = Files.newBufferedReader(inputFile)) {
			br.lines().forEach(report::addDiagnosticNumber);
		}
	}
}
