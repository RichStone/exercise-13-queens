
public class Chessboard {
	private int[][] chessboard;

	public Chessboard(int rows, int columns) {
		chessboard = new int[rows][columns];
	}

	public void initializeBoard() {
		for (int i = 0; i < chessboard.length; i++) {
			for (int j = 0; j < chessboard[i].length; j++) {
				chessboard[i][j] = -1;
			}
		}
	}

	public void printBoard() {
		// a return for formatting reasons
		System.out.println("\n");

		// print the letters of the board first
		for (int i = 0; i < chessboard.length; i++) {
			System.out.print("\t#" + (char) (i + 65) + "#");
		}

		// another return
		System.out.println("");
		for (int i = 0; i < chessboard.length; i++) {
			System.out.print("\n# " + (i + 1) + " #\t");
			for (int j = 0; j < chessboard[i].length; j++) {
				System.out.print(chessboard[i][j] + "\t");
			}
			System.out.println("");
		}
	}

	public void setQueen(int row, int column) {
		chessboard[row][column] = 0;
	}

	/**
	 * 
	 * @param rowCell_1
	 * @param columnCell_1
	 * @param rowCell_2
	 * @param columnCell_2
	 * @return
	 */
	public boolean threat(int row, int column, int[][] chessboard) {

		// horizontal threat
		for (int i = column - 1; i >= 0; i--) {
			if (chessboard[row][i] == 0) {
				return true;
			}
		}

		// vertical threat
		for (int i = row - 1; i >= 0; i--) {
			if (chessboard[i][column] == 0) {
				return true;
			}
		}

		// diagonal ascending left
		int tmpColumn = column - 1;

		for (int i = row - 1; i >= 0; i--) {

			if (tmpColumn < 0) {
				continue;
			}
			if (chessboard[i][tmpColumn] == 0) {
				return true;
			}

			tmpColumn--;
		}

		// diagonal ascending right
		tmpColumn = column + 1;

		for (int i = row - 1; i >= 0; i--) {

			if (tmpColumn == -1 || tmpColumn > 7) {
				continue;
			}
			if (chessboard[i][tmpColumn] == 0) {
				return true;
			}

			tmpColumn++;
		}

		return false;
	}
	
	//coordinates for last queen for backtracking
	int lastQueenRow;
	int lastQueenColumn;

	public int[][] findSolution(int[][] tempChessboard, int rowStart, int columnStart) {
		
		//little test
//		if(chessboard[0][1] == 0) {
//			System.out.println("Letztes Board erstmal");
//			printBoard();
//			return chessboard;
//		}
		// brauchen einen Solution Count
		int queensSet = 0;

		// erstmal termination condition
		if (allSolutionsFound()) {
			System.out.println("SUPPA!");
			printBoard();
			return chessboard;
		} else {
			for (int i = rowStart; i < chessboard.length; i++) {
				for (int j = columnStart; j < chessboard.length; j++) {
					
					if(!threat(i, j, chessboard)) {
						
						System.out.println("Keine Bedrohung :)");
						Queen queen = new Queen(i, j);
						
						setQueen(queen);
						
						queensSet++;
						
						findSolution(chessboard, queen.getRow() + 1, 0);
					}
					
					System.out.println("Bedrohung!");
				}
			}
		}
		
		printBoard();
		
		System.out.println("TRY BACKTRACKING");
		findSolution(deleteLastQueenAndGetNewBoard(), lastQueenRow, lastQueenColumn + 1);
		
		System.out.println("VERSAGT");
		return null;
	}
	
	/**
	 * find the last queen
	 * set the field to -1
	 * save the coordinates in the field variables lastQueenRow and lastQueenColumn
	 * @return the new chessboard
	 */
	public int[][] deleteLastQueenAndGetNewBoard() {
		System.out.println("BACKTRACKING STARTED");
		for(int i = chessboard.length - 1; i >= 0; i--) {
			System.out.println("Row checked");
			for(int j = chessboard.length - 1; j >= 0 ; j--) {
				if(chessboard[i][j] == 0) {
					System.out.println("LAST QUEEN FOUND!");
					chessboard[i][j] = -1;
					lastQueenColumn = j;
					lastQueenRow = i;
					printBoard();
					return chessboard;
				}
				System.out.println("Column checked");
			}
		}
		
		return null;
	}
	
	public boolean allSolutionsFound() {
		int amountsOfQueens = 0;
		for(int i = 0; i < chessboard.length; i++) {
			for(int j = 0; j < chessboard.length; j++) {
				if(chessboard[i][j] == 0) {
					amountsOfQueens++;
				}
				if(amountsOfQueens == chessboard.length) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void setQueen(Queen queen) {
		chessboard[queen.getRow()][queen.getColumn()] = 0;
	}

	public int[][] getBoard() {
		return chessboard;
	}
}
