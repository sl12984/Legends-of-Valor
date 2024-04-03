public class Board {
	
	public final static int DIMENSIONCEILING = 50;
	private Space[][] board;
	private int numRow; //number of rows on the board
	private int numCol; //number of columns on the board
	
	//input r represents the number of rows on the board
	//input c represents the number of columns on the board
	//input n indicates how many types of content can be placed in each space
	public Board(int r, int c, int n) {
		if (r < 1 || r > DIMENSIONCEILING || c < 1 || c > DIMENSIONCEILING || 
				n < 0 || n > Space.TYPENUMCEILING) {
			throw new IllegalArgumentException("Input invalid");
		}
		numRow = r;
		numCol = c;
		board = new Space[numRow][numCol];
		for (int i=0; i<numRow; i++) {
			for (int j=0; j<numCol; j++) {
				board[i][j] = new Space(n);
			}
		}
	}
	
	public int getNumRow() {
		return numRow;
	}
	
	public int getNumCol() {
		return numCol;
	}
	
	public Space getSpace(int r, int c) {
		if ((r<0 || r>=numRow) || (c<0 || c>=numCol)) {
			throw new IllegalArgumentException("Input invalid");
		}
		return board[r][c];
	}
	
	//returns the type of content currently at row r and column c
	public int getContentAt(int r, int c) {
		if ((r<0 || r>=numRow) || (c<0 || c>=numCol)) {
			throw new IllegalArgumentException("Input invalid");
		}
		return board[r][c].getCurrentType();
	}
	
	public boolean setSpace(int r, int c, Space s) {
		if (s == null || (r<0 || r>=numRow) || (c<0 || c>=numCol)) {
			throw new IllegalArgumentException("Input invalid");
		}
		board[r][c] = s;
		return true;
	}
	
	//takes in which row r and which column c to place and which type of content 
	//to be placed in the space (can only place in empty space)
	public boolean setContentAt(int r, int c, int type) throws Exception {
		if ((r<0 || r>=numRow) || (c<0 || c>=numCol)) {
			throw new IllegalArgumentException("Input invalid");
		}
		return board[r][c].setType(type);
	}
	
	//sets every space on the board to empty
	public void clearBoard() throws Exception {
		for (int i=0; i<numRow; i++) {
			for (int j=0; j<numCol; j++) {
				board[i][j].setType(0);
			}
		}
	}
	
	public void print() {
		for (int i=0; i<numRow; i++) {
			for (int j=0; j<numCol; j++) {
				board[i][j].print();
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public void print(int r, int c) {
		if ((r<0 || r>=numRow) || (c<0 || c>=numCol)) {
			throw new IllegalArgumentException("Input invalid");
		}
		board[r][c].print();
	}
}
