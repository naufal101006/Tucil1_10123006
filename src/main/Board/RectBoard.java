package main.Board;

import main.Block;

public class RectBoard extends Board {
    public RectBoard(int y, int x) {
        this.board = new Character[y][x];

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = '.';
            }
        }
    }

    public RectBoard clone() {
        RectBoard clone = new RectBoard(this.board.length, this.board[0].length);

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                clone.board[i][j] = this.board[i][j];
            }
        }

        return clone;
    }

    public boolean place(Block b, int y, int x) {
        boolean canPlace = true;
        int[] blockSize = b.size();

        for (int i = 0; i < blockSize[0]; i++) {
            for (int j = 0; j < blockSize[1]; j++) {
                if ((!isEmpty(y+i, x+j) && b.block[i][j] != '.') || !canPlace) {
                    canPlace = false;
                    break;
                }
            }
        }

        if (canPlace) {
            b.iterate((c, i, j) -> {
                if (c != '.') {
                    this.board[y+i][x+j] = c;
                }
            });
        }

        return canPlace;
    }

    public boolean isEmpty(int y, int x) {
        if (y < this.board.length && x < this.board[0].length) {
            return this.board[y][x] == '.';
        }
        return false;
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