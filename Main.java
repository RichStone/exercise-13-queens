
public class Main {

	public static void main(String[] args) {
		Chessboard chessboard = new Chessboard(8, 8);
		Queen queen1 = new Queen(1, 6);
		Queen queen2 = new Queen(3, 3);
		chessboard.initializeBoard();
		chessboard.printBoard();
		chessboard.setQueen(queen1.getRow(), queen1.getColumn());
		chessboard.setQueen(queen2.getRow(), queen2.getColumn());
		chessboard.printBoard();
		System.out.println("\nThreat: " + chessboard.threat(queen1.getRow(), queen1.getColumn(), queen2.getRow(), queen2.getColumn()));
	}

}
