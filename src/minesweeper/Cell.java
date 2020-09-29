package minesweeper;

public class Cell {
    private char symbol = '0';
    private boolean visible = false;
    private boolean marked = false;

    public char getSymbol() {
        return symbol;
    }

    protected void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public boolean isVisible() {
        return visible;
    }

    protected void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isMarked() {
        return marked;
    }

    protected void setMarked(boolean marked) {
        this.marked = marked;
    }

    public boolean isANumber() {
        return symbol >= '1' && symbol <= '9';
    }

    public void increment() {
        symbol++;
    }

    protected boolean hasMine() {
        return symbol == View.getMINE_CHAR();
    }

    protected boolean isEmpty() {
        return !isANumber() && !hasMine();
    }
}