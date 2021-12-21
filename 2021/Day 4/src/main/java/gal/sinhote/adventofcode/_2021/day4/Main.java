package gal.sinhote.adventofcode._2021.day4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

	private static final String REPORT_FORMAT = "%s winning board was:\n%s\nLast number drawn for that board was: %d.\nThe sum of the remaining figures of the board is: %d.\nBoard score is: %d.";

	public static void main(String[] args) {

		if (args.length == 0) {
			System.err.println("Expected a parameter containing an input file name");
			return;
		}

		URL inputFileURL = Main.class.getResource(args[0]);

		if (inputFileURL == null) {
			System.err.println("Resource could not be found in classpath: " + args[0]);
		} else {
			try {
				Path filePath = Paths.get(inputFileURL.toURI());
				BingoGame bingo = BingoGame.fromFile(filePath);

				bingo.playGame();

				List<BingoBoard> winningBoards = bingo.getWinningBoards();

				if (winningBoards.isEmpty()) {
					System.out.println("Game finished with no winning boards");
				} else {
					System.out.println("Bingo!!!");
					System.out.println(reportForBoard(winningBoards.get(0), "First"));
					System.out.println();
					System.out.println(reportForBoard(winningBoards.get(winningBoards.size() - 1), "Last"));
				}
			} catch (URISyntaxException use) {
				System.err.println("Malformed input file URI: " + use.getMessage());
			} catch (IOException ioe) {
				System.err.println("Error reading input file: " + ioe.getMessage());
			} catch (IllegalArgumentException iae) {
				System.err.println("Input file has an incorrect format: " + iae.getMessage());
			} catch (BoardHasBingoException bhbe) {
				System.err.println(
						"Bingo game declared there already was a bingo without even playing! " + bhbe.getMessage());
			}

		}
	}

	private static String reportForBoard(BingoBoard board, String qualifier) {
		return String.format(REPORT_FORMAT, qualifier, board.prettyPrint(), board.getLastNumberDrawn(),
				board.sumRemainingNumbers(), board.getScore());
	}
}
