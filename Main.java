
public class Main {

	public static void main(String[] args) {
		Chessboard cb = new Chessboard(8, 8);
		cb.initializeBoard();
		cb.findSolution(cb.getBoard(), 0, 1);
		cb.printBoard();
	}
	
	public void testThreat() {
		Chessboard chessboard = new Chessboard(8, 8);
		Queen queen1 = new Queen(0, 3);
		Queen queen2 = new Queen(1, 0);
		chessboard.initializeBoard();
		chessboard.printBoard();
		chessboard.setQueen(queen1.getRow(), queen1.getColumn());
		chessboard.setQueen(queen2.getRow(), queen2.getColumn());
		chessboard.printBoard();
		System.out.println("\nThreat: " + chessboard.threat(queen2.getRow(), queen2.getColumn(), chessboard.getBoard()));
	}
}
