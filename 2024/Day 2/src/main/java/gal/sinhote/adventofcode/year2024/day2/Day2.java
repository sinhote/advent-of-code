package gal.sinhote.adventofcode.year2024.day2;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import gal.sinhote.adventofcode.year2024.day2.ReportSafetyChecker.ReportSafety;

public class Day2 {


	public static void main(String[] argv) {
		if (argv.length < 1) {
			System.err.println("An input file must be provided!");
			return;
		}

		final ReportSafetyChecker checker = new ReportSafetyChecker();
		try {
			final Map<ReportSafetyChecker.ReportSafety, List<List<Integer>>> reportsBySafety =
					Files.lines(Paths.get(argv[0]))
					.map(Day2::parseReport)
					.collect(Collectors.groupingBy(
							checker::checkReportSafety,
							() -> new EnumMap<>(ReportSafety.class),
							Collectors.toList()));

			final int numberOfSafeReportsWithoutDampener = reportsBySafety.getOrDefault(ReportSafety.SAFE, emptyList()).size();
			final int numberOfSafeReportsWithDampener = reportsBySafety.getOrDefault(ReportSafety.SAFE_WITH_DAMPENER, emptyList()).size();
			final int numberOfUnsafeReports = reportsBySafety.getOrDefault(ReportSafety.UNSAFE, emptyList()).size();
			System.out.printf("There are %d safe reports, of which:\n", numberOfSafeReportsWithoutDampener + numberOfSafeReportsWithDampener);
			System.out.printf("\t* %d are safe without applying the Problem Dampener\n", numberOfSafeReportsWithoutDampener);
			System.out.printf("\t* %d are safe with the Problem Dampener applied\n", numberOfSafeReportsWithDampener);
			System.out.printf("There are %d unsafe reports\n", numberOfUnsafeReports);
			System.out.println();

		} catch (IOException e) {
			System.err.println("Couldn't read input file " + argv[0]);
		}
	}

	private static List<Integer> parseReport(final String reportString) {
		return Stream.of(reportString.trim().split("\\s+")).map(Integer::valueOf).collect(toList());
	}
}