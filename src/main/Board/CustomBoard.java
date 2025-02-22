package main.Board;

import main.Block;

public class CustomBoard extends Board {
    public CustomBoard(int y, int x) {
        this.board = new Character[y][x];

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = '.';
            }
        }
    }

    public CustomBoard(int y, int x, String layout) {
        this.board = new Character[y][x];
        String[] parts = layout.split("\\r?\\n");

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (parts[i].charAt(j) == '.') {
                    this.board[i][j] = ' ';
                } else if (parts[i].charAt(j) == 'X') {
                    this.board[i][j] = '.';
                }
            }
        }
    }

    public CustomBoard clone() {
        CustomBoard clone = new CustomBoard(this.board.length, this.board[0].length);

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
}