package main.Board;

import main.Block;

public abstract class Board {
    public Character[][] board;


    public abstract Board clone();
    public abstract boolean place(Block b, int x, int y);
    public boolean isEmpty(int y, int x) {
        if (y < this.board.length && x < this.board[0].length) {
            return this.board[y][x] == '.';
        }
        return false;
    }

    public boolean isOccupied(int y, int x) {
        if (y < this.board.length && x < this.board[0].length) {
            return Character.isLetterOrDigit(this.board[y][x]);
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.board.length; i++) {

            for (int j = 0; j < this.board[i].length; j++) {
                sb.append(this.board[i][j]);
            }
            
            sb.append("\n");
        }
        return sb.toString();
    }

    public void print() {
        System.out.println(toString());
    }
}