package Board;

public class RectBoard extends Board {
    private Character[][] board;

    public RectBoard(int y, int x) {
        this.board = new Character[y][x];

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = '.';
            }
        }
    }

    public void place() {

    }

    public void isEmpty(int x, int y) {

    }

    public void print() {
        for (int i = 0; i < this.board.length; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < this.board[i].length; j++) {
                sb.append(this.board[i][j]);
            }
            
            System.out.println(sb.toString());
        }
    }
}