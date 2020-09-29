package minesweeper;

public class View {

    private final Cell[][] field;
    private static final char MINE = 'X';
    private static final char MARKED_CELL = '*';
    private static final char SECRET_CELL = '.';
    private static final char EMPTY_CELL = '/';

    public View(Cell[][] minefield) {
        this.field = minefield;
    }

    protected void printMinefield() {
        printHeader();
        printBorder();
        for (int j = 0; j < field.length; j++) {
            System.out.print((j + 1) + "|");
            for (int i = 0; i < field[j].length; i++) {
                printCell(field[j][i]);
            }
            System.out.println("|");
        }
        printBorder();
    }

    private void printHeader() {
        System.out.print(" |");
        for (int i = 0; i < field.length; i++) {
            System.out.print(i + 1);
        }
        System.out.println("|");
    }

    private void printBorder() {
        System.out.print("-|");
        for (int i = 0; i < field.length; i++) {
            System.out.print("-");
        }
        System.out.println("|");
    }

    private void printCell(Cell cell) {
        if (!cell.isVisible() && !cell.isMarked()) {
            System.out.print(SECRET_CELL);
        } else if (cell.isMarked()) {
            System.out.print(MARKED_CELL);
        } else if (cell.isEmpty()) {
            System.out.print(EMPTY_CELL);
        } else {
            System.out.print(cell.getSymbol());
        }
    }

    protected static char getMINE_CHAR() {
        return MINE;
    }
}