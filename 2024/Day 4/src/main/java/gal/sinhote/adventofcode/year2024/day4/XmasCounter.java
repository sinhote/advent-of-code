package gal.sinhote.adventofcode.year2024.day4;

import java.util.List;
import java.util.Objects;

public class XmasCounter {

	private final List<String> searchField;
	private final long numberOfXmas;

	public XmasCounter(final List<String> searchField) {
		this.searchField = Objects.requireNonNull(searchField);
		numberOfXmas = countXmas();
	}

	public long getNumberOfXmas() {
		return numberOfXmas;
	}

	private long countXmas() {
		long sum = 0;

		for (int y = 0; y < searchField.size(); y++) {
			final String row = searchField.get(y);
			int x = row.indexOf('X');

			while(x != -1) {
				sum += checkUp(x, y) +
						checkUpRight(x, y) +
						checkRight(x, y) +
						checkDownRight(x, y) +
						checkDown(x, y) +
						checkDownLeft(x, y) +
						checkLeft(x, y) +
						checkUpLeft(x, y);
				x = row.indexOf('X', x+1);
			}
		}
		return sum;
	}

	private int checkUp(final int x, final int y) {
		return y >= 3 &&
				searchField.get(y-1).charAt(x) == 'M' &&
				searchField.get(y-2).charAt(x) == 'A' &&
				searchField.get(y-3).charAt(x) == 'S'
				? 1 : 0;
	}

	private int checkUpRight(final int x, final int y) {
		return y >= 3 &&
				x <= searchField.get(y-1).length()-2 &&
				searchField.get(y-1).charAt(x+1) == 'M' &&
				x <= searchField.get(y-2).length()-3 &&
				searchField.get(y-2).charAt(x+2) == 'A' &&
				x <= searchField.get(y-3).length()-4 &&
				searchField.get(y-3).charAt(x+3) == 'S'
				? 1 : 0;
	}

	private int checkRight(final int x, final int y) {
		final String row = searchField.get(y);
		return x <= row.length() - 4 &&
				row.charAt(x+1) == 'M' &&
				row.charAt(x+2) == 'A' &&
				row.charAt(x+3) == 'S'
				? 1 : 0;
	}

	private int checkDownRight(final int x, final int y) {
		return y <= searchField.size()-4 &&
				x <= searchField.get(y+1).length()-2 &&
				searchField.get(y+1).charAt(x+1) == 'M' &&
				x <= searchField.get(y+2).length()-3 &&
				searchField.get(y+2).charAt(x+2) == 'A' &&
				x <= searchField.get(y+3).length()-4 &&
				searchField.get(y+3).charAt(x+3) == 'S'
				? 1 : 0;
	}

	private int checkDown(final int x, final int y) {
		return y <= searchField.size() - 4 &&
				searchField.get(y+1).charAt(x) == 'M' &&
				searchField.get(y+2).charAt(x) == 'A' &&
				searchField.get(y+3).charAt(x) == 'S'
				? 1 : 0;
	}

	private int checkDownLeft(final int x, final int y) {
		return x >= 3 && y <= searchField.size()-4 &&
				searchField.get(y+1).charAt(x-1) == 'M' &&
				searchField.get(y+2).charAt(x-2) == 'A' &&
				searchField.get(y+3).charAt(x-3) == 'S'
				? 1 : 0;
	}

	private int checkLeft(final int x, final int y) {
		final String row = searchField.get(y);
		return x >= 3 &&
				row.charAt(x-1) == 'M' &&
				row.charAt(x-2) == 'A' &&
				row.charAt(x-3) == 'S'
				? 1 : 0;
	}

	private int checkUpLeft(final int x, final int y) {
		return x >= 3 && y >= 3 &&
				searchField.get(y-1).charAt(x-1) == 'M' &&
				searchField.get(y-2).charAt(x-2) == 'A' &&
				searchField.get(y-3).charAt(x-3) == 'S'
				? 1 : 0;
	}
}
