package gal.sinhote.adventofcode._2021.day4;

import java.util.Objects;

public class BingoPosition {
	private int row;
	private int column;

	public BingoPosition(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof BingoPosition) {
			BingoPosition that = (BingoPosition) o;
			return this.row == that.row && this.column == that.column;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, column);
	}
}
