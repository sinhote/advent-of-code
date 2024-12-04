package gal.sinhote.adventofcode.year2024.day3;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MulFinder {

	private static final Pattern MUL_PATTERN = Pattern.compile("mul\\(([0-9]{1,3}),([0-9]{1,3})\\)");

	private long sum = 0;

	public long getSum() {
		return sum;
	}

	public void addLine(final String line) {
		final Matcher matcher = MUL_PATTERN.matcher(line);

		while(matcher.find()) {
			sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
		}
	}

	public void addLines(final Collection<String> lines) {
		lines.forEach(this::addLine);
	}

	public void addLines(final Stream<String> lines) {
		lines.forEach(this::addLine);
	}
}
