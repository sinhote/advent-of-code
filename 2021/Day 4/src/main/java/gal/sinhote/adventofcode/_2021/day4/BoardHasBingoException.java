package gal.sinhote.adventofcode._2021.day4;

public class BoardHasBingoException extends Exception {

	private static final long serialVersionUID = 4477572121688502705L;

	BingoBoard theBoard;

	public BoardHasBingoException(BingoBoard board) {
		super();
		theBoard = board;
	}

	public BoardHasBingoException(BingoBoard board, Throwable cause) {
		super(cause);
		theBoard = board;
	}

	public BoardHasBingoException(BingoBoard board, String message) {
		super(message);
		theBoard = board;
	}

	public BoardHasBingoException(BingoBoard board, String message, Throwable cause) {
		super(message, cause);
		theBoard = board;
	}

	public BingoBoard getBoard() {
		return theBoard;
	}
}
