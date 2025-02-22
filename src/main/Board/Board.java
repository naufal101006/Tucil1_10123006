package main.Board;

import main.Block;

public abstract class Board {
    protected Character[][] board;

    public abstract boolean place(Block b, int x, int y);
    public abstract boolean isEmpty(int x, int y);
    public abstract void print();
}