package gal.sinhote.adventofcode.year2024.day2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportSafetyChecker {

	public enum ReportSafety {
		UNSAFE, SAFE_WITH_DAMPENER, SAFE;
	}

	public ReportSafety checkReportSafety(final List<Integer> report) {
		if (report.size() < 2) {
			return ReportSafety.SAFE;
		}

		final int increaseTrend = Integer.signum(report.get(1) - report.get(0));

		boolean isUnsafe = false;
		for (int i = 1; i < report.size(); i++) {
			final int difference = (report.get(i) - report.get(i-1)) * increaseTrend;

			if (difference < 1 || difference > 3) {
				isUnsafe = true;
				break;
			}
		}

		if (isUnsafe) {
			for(int i = 0; i < report.size(); i++) {
				final List<Integer> skippedReport = Stream.concat(report.subList(0, i).stream(), report.subList(i+1, report.size()).stream()).collect(Collectors.toList());
				if (checkReportSafety(skippedReport) == ReportSafety.SAFE) {
					return ReportSafety.SAFE_WITH_DAMPENER;
				}
			}
			return ReportSafety.UNSAFE;
		}

		return ReportSafety.SAFE;
	}
}
