
public class Chessboard {
	private int[][] chessboard;
	private int boardSize;

	public Chessboard(int rows, int columns) {
		chessboard = new int[rows][columns];
		boardSize = chessboard.length;
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

	// coordinates for last queen for backtracking
	int lastQueenRow;
	int lastQueenColumn;

	public int[][] findSolution(int[][] tempChessboard, int row, int column) {
		
		boolean boardFinished = row == boardSize - 1 && column == boardSize - 1;
		
		if (allSolutionsFound()) {
			System.out.println("SUPPA!");
			printBoard();
			return tempChessboard;
		}
		else {
			
			if(!threat(row, column, tempChessboard)) {
				System.out.println("Keine Bedrohung :)");
				setQueen(row, column);
				printBoard();
			}
			
			//check fields while board not completely checked
			if(!boardFinished) {
				
				System.out.println("Check das Board");
				
				//check next column
				column++;
				
				//increase row when column reached the edge
				if(column == boardSize) {
					row++;
					column = 0;
				}
				
			}
			//track back when end of board reached
			else {
				//special case
				if(row == boardSize - 1 && column == boardSize - 1 && chessboard[row][column] == 0) {
					System.out.println("LAST QUEEN SET BUT NOT ALL SOLUTIONS FOUND");
					chessboard[row][column] = -1;
				}
				
				System.out.println("End of Board reached XX");
				
				//assign last pos of queen as actual pos
				int[] lastPositions = deleteLastQueenAndGetLastPositions(column);
				row = lastPositions[0];
				column = lastPositions[1];
				
				//now get the actual positions inlusively the Randbehandlung
				//try one after last queen's pos
				//check next column
				column++;
				
				//increase row when column reached the edge
				if(column == boardSize) {
					row++;
					column = 0;
				}
				if(row >= boardSize) {
					row = boardSize - 1;
				}
				
			}
			
			return findSolution(tempChessboard, row, column);
		}
	}

	/**
	 * find the last queen set the field to -1 save the coordinates in 
	 * an array -> row at position [0] -> column at position [1]
	 * @return array with the coordinates
	 */
	public int[] deleteLastQueenAndGetLastPositions(int column) {
		int[] lastPositions = new int [2];
		System.out.println("BACKTRACKING STARTED");
		for (int i = boardSize - 1; i >= 0; i--) {
			for (int j = boardSize - 1; j >= 0; j--) {
				if (chessboard[i][j] == 0) {
					System.out.println("LAST QUEEN FOUND AT " + i + " and " + j);
					
					//delete the queen
					chessboard[i][j] = -1;
					
					//save the row
					lastPositions[0] = i;
					
					//save the column
					lastPositions[1] = j;
					
					printBoard();
					
					return lastPositions;
				}
				System.out.println("Column checked");
			}
		}
		
		System.out.println("DELETE QUEENS METHDO VERSATT");
		return null;
	}

	public boolean allSolutionsFound() {
		
		int amountOfQueens = 0;
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (chessboard[i][j] == 0) {
					amountOfQueens++;
				}
				if (amountOfQueens == 8) {
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
