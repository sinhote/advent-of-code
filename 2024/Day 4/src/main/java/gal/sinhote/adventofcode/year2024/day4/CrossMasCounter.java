package gal.sinhote.adventofcode.year2024.day4;

import java.util.List;
import java.util.Objects;

public class CrossMasCounter {

	private final List<String> searchField;
	private final long numberOfXmas;

	public CrossMasCounter(final List<String> searchField) {
		this.searchField = Objects.requireNonNull(searchField);
		numberOfXmas = countXmas();
	}

	public long getNumberOfXmas() {
		return numberOfXmas;
	}

	private long countXmas() {
		long sum = 0;

		// Exclude first and last indexes for the central 'A', as it requires having a
		// row above and below
		for (int y = 1; y < searchField.size() - 1; y++) {
			final String row = searchField.get(y);
			int x = row.indexOf('A');

			while (x != -1) {
				// Exclude first and last indexes, as the X requires having a column before and after
				if (x > 0 && x < searchField.get(y).length() - 1) {
					sum += checkCross(x, y);
				}
				x = row.indexOf('A', x + 1);
			}
		}
		return sum;
	}

	private int checkCross(final int x, final int y) {
		return checkMainDiagonal(x, y) &&
				checkAntidiagonal(x, y)
				? 1 : 0;
	}

	private boolean checkMainDiagonal(final int x, final int y) {
		char upLeft = searchField.get(y-1).charAt(x-1);
		char downRight = searchField.get(y+1).charAt(x+1);

		return (upLeft == 'M' && downRight == 'S') || (upLeft == 'S' && downRight == 'M');
	}

	private boolean checkAntidiagonal(final int x, final int y) {
		char upRight = searchField.get(y-1).charAt(x+1);
		char downLeft = searchField.get(y+1).charAt(x-1);

		return (upRight == 'M' && downLeft == 'S') || (upRight == 'S' && downLeft == 'M');
	}
}
