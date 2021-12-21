package gal.sinhote.adventofcode._2021.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BingoGame {

	private static final String INPUT_FILE_INCORRECT = "Input file does not have the correct format";

	List<BingoBoard> boards;
	Set<Integer> numbers;

	boolean gameFinished;
	List<BingoBoard> winningBoards;

	public BingoGame() {
		boards = new ArrayList<>();
		numbers = new LinkedHashSet<>();
		gameFinished = false;
		winningBoards = new ArrayList<>();
	}

	public static BingoGame fromFile(Path theFile) throws IOException {
		BingoGame retVal = new BingoGame();
		try (BufferedReader br = Files.newBufferedReader(theFile)) {
			retVal.addNumbers(Stream.of(br.readLine().split(",")).map(Integer::parseInt).collect(Collectors.toList()));

			String emptyLine = br.readLine();

			if (emptyLine == null || !emptyLine.isEmpty() || !br.ready()) {
				throw new IllegalArgumentException(INPUT_FILE_INCORRECT);
			}

			while (br.ready()) {
				retVal.addBoard(BingoBoard.fromReader(br));
			}
		} catch (NullPointerException npe) {
			throw new IllegalArgumentException(INPUT_FILE_INCORRECT, npe);
		}

		return retVal;
	}

	public void addNumber(int number) {
		if (gameFinished) {
			throw new IllegalStateException("Game is already finished");
		}
		numbers.add(number);
	}

	public void addNumbers(Collection<Integer> numbers) {
		if (gameFinished) {
			throw new IllegalStateException("Game is already finished");
		}
		this.numbers.addAll(numbers);
	}

	public void addBoard(BingoBoard board) {
		if (gameFinished) {
			throw new IllegalStateException("Game is already finished");
		}
		boards.add(board);
	}

	public void playGame() throws BoardHasBingoException {
		if (!gameFinished) {
			try {
				for (int number : numbers) {
					Iterator<BingoBoard> iter = boards.iterator();
					while(iter.hasNext()) {
						BingoBoard currentBoard = iter.next();
						if (currentBoard.drawNumber(number)) {
							// Bingo!
							winningBoards.add(currentBoard);
							iter.remove();
						}
					}
					if (boards.isEmpty()) {
						break;
					}
				}
			} finally {
				gameFinished = true;
			}
		} else {
			throw new IllegalStateException("Game has already been played");
		}
	}

	public boolean isGameFinished() {
		return gameFinished;
	}

	public List<BingoBoard> getWinningBoards() {
		if (!gameFinished) {
			throw new IllegalStateException("Game has not been played yet");
		}
		return Collections.unmodifiableList(winningBoards);
	}
}
