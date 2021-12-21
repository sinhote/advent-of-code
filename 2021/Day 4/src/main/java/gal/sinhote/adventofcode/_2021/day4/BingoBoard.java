package gal.sinhote.adventofcode._2021.day4;

import static java.lang.Math.ceil;
import static java.lang.Math.log10;
import static java.util.Collections.emptySet;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BingoBoard {

	private List<List<Integer>> numbers;
	private String prettyPrintStr = null;

	private List<Integer> remainingPerRow;
	private List<Integer> remainingPerColumn;

	private Map<Integer, Set<BingoPosition>> numberPositions;

	int lastNumberDrawn;


	private boolean hasBingo = false;

	public BingoBoard(List<List<Integer>> numbers) {
		// Get the lengths of all rows and put them in a list
		remainingPerRow = numbers.stream().map(row -> row.size()).collect(toList());

		// Make sure all rows have the same length and that input is not empty
		int[] rowLengths = remainingPerRow.stream().mapToInt(Integer::intValue).distinct().toArray();
		if (rowLengths.length == 0) {
			throw new IllegalArgumentException("Input array must not be empty");
		} else if (rowLengths.length > 1) {
			throw new IllegalArgumentException("Rows must all have the same length");
		} else {
			remainingPerColumn = new ArrayList<>(Collections.nCopies(rowLengths[0], numbers.size()));
			numberPositions = IntStream.range(0, numbers.size())
					.mapToObj(rowIndex -> IntStream.range(0, numbers.get(rowIndex).size())
							.mapToObj(colIndex -> new BingoPosition(rowIndex, colIndex)))
					.flatMap(identity())
					.collect(groupingBy(pos -> numbers.get(pos.getRow()).get(pos.getColumn()), toSet()));

		}

		this.numbers = numbers;
		lastNumberDrawn = -1;
	}

	public static BingoBoard fromReader (BufferedReader reader) throws IOException {

		List<List<Integer>> inputNumbers = new ArrayList<>();

		String line = reader.readLine();
		while (line != null && !line.isEmpty()) {
			inputNumbers.add(Stream.of(line.trim().split("\\s+")).map(Integer::parseInt).collect(toList()));
			line = reader.readLine();
		}

		return new BingoBoard(inputNumbers);
	}

	public boolean drawNumber(int number) throws BoardHasBingoException {
		if (!hasBingo) {
			hasBingo = numberPositions.getOrDefault(number, emptySet()).stream().map(this::decrementRemaining)
					.reduce(Boolean::logicalOr).orElse(false);
			numberPositions.replace(number, emptySet());

			if (hasBingo) {
				lastNumberDrawn = number;
			}

			return hasBingo;
		} else {
			throw new BoardHasBingoException(this, "There is already a bingo. No more numbers can be drawn.");
		}
	}

	public int sumRemainingNumbers() {
		return numberPositions.entrySet().stream().reduce(0,
				(sum, entry) -> entry.getKey() * entry.getValue().size() + sum, Integer::sum);
	}

	public int getLastNumberDrawn() {
		if (!hasBingo) {
			throw new IllegalStateException("There is no bingo in this board");
		}
		return lastNumberDrawn;
	}

	public int getScore() {
		if (!hasBingo) {
			throw new IllegalStateException("There is no bingo in this board");
		}
		return sumRemainingNumbers() * lastNumberDrawn;
	}

	private boolean decrementRemaining(BingoPosition position) {
		boolean hasBingoInRows = decrementListElement(remainingPerRow, position.getRow());
		boolean hasBingoInColumns = decrementListElement(remainingPerColumn, position.getColumn());

		return hasBingoInRows || hasBingoInColumns;
	}

	private boolean decrementListElement(List<Integer> theList, int theIndex) {
		int value = theList.get(theIndex);

		theList.set(theIndex, --value);

		return value == 0;
	}

	@Override
	public String toString() {
		return numbers.toString();
	}

	public String prettyPrint() {
		if (prettyPrintStr == null) {
			// Find the largest number, so that we know how many positions we have to use in toString
			final int padding = (int) ceil(log10(numbers.stream().flatMap(List<Integer>::stream).mapToInt(Integer::intValue).max().orElse(1)));
			prettyPrintStr = numbers.stream().map(row -> row.stream().map(n -> String.format("%"+padding+"d", n)).collect(joining(" "))).collect(joining("\n"));
		}

		return prettyPrintStr;
	}
}
