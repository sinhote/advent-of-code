package gal.sinhote.adventofcode.year2024.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListParser {

	public static final class ParsedLists {
		public final List<Integer> leftList;
		public final List<Integer> rightList;

		public ParsedLists(final List<Integer> leftList, final List<Integer> rightList) {
			this.leftList = new ArrayList<>(leftList);
			this.rightList = new ArrayList<>(rightList);
		}
	}

	private static final Pattern INPUT_PATTERN = Pattern.compile("^\\s*([0-9]+)\\s+([0-9]+)\\s*$");


	private final List<Integer> leftList = new ArrayList<>();
	private final List<Integer> rightList = new ArrayList<>();

	public ParsedLists parseLists(final String fileLocation) throws IOException {
		Files.lines(Paths.get(fileLocation)).forEach(this::parseInputLine);

		return new ParsedLists(leftList, rightList);
	}

	private void parseInputLine(final String line) {
		final Matcher lineMatcher = INPUT_PATTERN.matcher(line);

		if (!lineMatcher.matches()) {
			throw new IllegalArgumentException("Input file error. Expected a line with two integers separated by white spaces; got \"" + line + "\"");
		}

		try {
			leftList.add(Integer.parseInt(lineMatcher.group(1)));
			rightList.add(Integer.parseInt(lineMatcher.group(2)));
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Could not parse integer in line: \"" + line + "\"", nfe);
		}
	}

}
