package Board;

public abstract class Board {
    private String[] board;

    public abstract void place();
    public abstract void isEmpty(int x, int y);
    public abstract void print();
}