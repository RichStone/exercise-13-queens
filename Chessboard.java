
public class Chessboard {
	private int[][] chessboard;
	
	public Chessboard(int rows, int columns) {
		chessboard = new int[rows][columns];
	}

	public void initializeBoard() {
		for(int i = 0; i < chessboard.length; i++) {
			for(int j = 0; j < chessboard[i].length; j++) {
				chessboard[i][j] = -1;
			}
		}
	}
	
	public void printBoard() {
		//a return for formatting reasons
		System.out.println("\n");
		
		//print the letters of the board first
		for(int i = 0; i < chessboard.length; i++) {
			System.out.print("\t#" + (char)(i + 65) + "#");
		}
		
		//another return
		System.out.println("");
		for(int i = 0; i < chessboard.length; i++) {
			System.out.print("\n# " + (i + 1) + " #\t");
			for(int j = 0; j < chessboard[i].length; j++) {
				System.out.print(chessboard[i][j] + "\t");
			}
			System.out.println("");
		}
	}
	
	public void setQueen(int row, int column) {
		chessboard[row][column] = 0;
	}
	
	public boolean threat(int rowCell_1, int columnCell_1, int rowCell_2, int columnCell_2) {
		boolean threatens = false;
		
		//vertical threat
		if(rowCell_1 == rowCell_2) {
			threatens = true;
		}
		if(columnCell_1 == columnCell_2) {
			threatens = true;
		}
		
		return threatens;
	}
	
	
	
	
}
