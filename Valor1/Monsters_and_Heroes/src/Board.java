public class Board {
    private int row;
    private int col;
    private Cell[][] board;

    public Board(int row, int col) {
        this.row = row;
        this.col = col;
        this.board = new Cell[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = new NormalCell();
            }
        }
    }

    // Get the number of rows
    public int getRow() {
        return this.row;
    }

    // Get the number of columns
    public int getCol() {
        return this.col;
    }

    // Return the board
    public Cell[][] getBoard() {
        return this.board;
    }

    // Set Cell
    public void setCell(int row, int col, Cell c){
        board[row][col] = c;
    }

    // Print the Board
    public void print(){
        System.out.print("   ");
        for(int j=0;j<col;j++){
            System.out.print((j+1)+"  ");
        }
        System.out.println("");
        for(int i=0; i<row; i++){
            System.out.print((i+1)+"  ");
            for(int j=0; j<col; j++){
                System.out.print(board[i][j].toString());
            }
            System.out.println("");
        }
    }
}
