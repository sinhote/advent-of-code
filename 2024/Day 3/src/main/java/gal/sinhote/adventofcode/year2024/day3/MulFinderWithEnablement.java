package gal.sinhote.adventofcode.year2024.day3;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MulFinderWithEnablement {

	private static final Pattern MUL_PATTERN = Pattern.compile("mul\\(([0-9]{1,3}),([0-9]{1,3})\\)");
	private static final Pattern DO_PATTERN = Pattern.compile("do\\(\\)");
	private static final Pattern DONT_PATTERN = Pattern.compile("don't\\(\\)");

	private long sum = 0;
	private boolean isEnabled = true;

	public long getSum() {
		return sum;
	}

	public void addLine(final String line) {
		final Matcher mulMatcher = MUL_PATTERN.matcher(line);
		final Matcher doMatcher = DO_PATTERN.matcher(line);
		final Matcher dontMatcher = DONT_PATTERN.matcher(line);

		boolean keepGoing;
		do {
			keepGoing = false;
			if (isEnabled) {
				if (dontMatcher.find()) {
					doMatcher.region(dontMatcher.end(), doMatcher.regionEnd());
					mulMatcher.region(mulMatcher.regionStart(), dontMatcher.start());
					isEnabled = false;
					keepGoing = true;
				}

				while(mulMatcher.find()) {
					sum += Integer.parseInt(mulMatcher.group(1)) * Integer.parseInt(mulMatcher.group(2));
				}
			} else if (doMatcher.find()) {
				dontMatcher.region(doMatcher.end(), dontMatcher.regionEnd());
				mulMatcher.region(doMatcher.end(), dontMatcher.regionEnd());
				isEnabled = true;
				keepGoing = true;
			}
		} while (keepGoing);
	}

	public void addLines(final Collection<String> lines) {
		lines.forEach(this::addLine);
	}

	public void addLines(final Stream<String> lines) {
		lines.forEach(this::addLine);
	}
}
