package main.Board;

import main.Block;

public abstract class Board {
    public Character[][] board;


    public abstract Board clone();
    public abstract boolean place(Block b, int x, int y);
    public abstract boolean isEmpty(int x, int y);
    public abstract void print();
}