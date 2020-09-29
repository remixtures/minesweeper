package minesweeper;

import java.util.*;

class MineBoard {

    private static final Random randomNumber = new Random();
    private static final Scanner input = new Scanner(System.in);
    private static final Cell[][] mineField = new Cell[9][9];
    private View view = new View(mineField);
    int[][] neighbours = { {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    protected void start() {
        buildField();
        spreadMines();
        markMines();
        view.printMinefield();
        while (true) {
            userInterface();
            view.printMinefield();
            if (gameIsWon()) {
                System.out.println("Congratulations! You found all mines!");
                return;
            }
        }
    }

    private void userInterface() {
        System.out.print("Set/unset mines marks or claim a cell as free: ");
        String[] userCommands = input.nextLine().split("\\s+");
        if (userCommands.length != 3) {
            System.out.println("You must enter two numbers - from 1 to 9 - followed by the option 'free' or 'mine'");
            return;
        }
        int j = Integer.parseInt(userCommands[1]) - 1;
        int i = Integer.parseInt(userCommands[0]) - 1;
        String option = userCommands[2];

        Cell cell = mineField[j][i];
        if ("mine".equals(option)) {
            if (cell.isANumber() && cell.isVisible()) {
                System.out.println("There is a number here");
                userInterface();
           } else {
                cell.setMarked(!cell.isMarked());
          }
        } else if ("free".equals(option) && cell.hasMine()) {
            getMapOfMines();
            System.out.println("You stepped on a mine and failed!");
            System.exit(0);
        } else if ("free".equals(option)) {
            if (cell.isANumber() && cell.isVisible()) {
                System.out.println("There is a number here");
                userInterface();
            } else {
                uncoverFreeCells(j, i, null);
           }
        } else {
            System.out.println("Coordinates out of range || Invalid option. You must enter 'free' or 'mine'");
            userInterface();
        }
    }

    private void getMapOfMines() {
        for (int j = 0; j < mineField.length; j++) {
            for (int i = 0; i < mineField[j].length; i++) {
                Cell cell = mineField[j][i];
                cell.setMarked(false);
                if (cell.hasMine()) {
                    cell.setVisible(true);
                }
            }
        }
    }

    private void uncoverFreeCells(int j, int i, List<int[]> checked) {
        if (j < 0 || j >= mineField.length || i < 0 || i >= mineField[0].length) {
            return;
        }
        if (checked != null) {
            for (int[] cells : checked) {
                if (cells[0] == j && cells[1] == i) {
                    return;
                }
            }
        }
        Cell cell = mineField[j][i];
        cell.setVisible(true);
        cell.setMarked(false);
        if (cell.isANumber()) {
            return;
        }
        if (checked == null) {
            checked = new ArrayList<>();
        }
        checked.add(new int[]{j, i});
        for (int[] neighbour : neighbours) {
            uncoverFreeCells(j - neighbour[0], i - neighbour[1], checked);
        }
    }

    private static void buildField() {
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                mineField[j][i] = new Cell();
            }
        }
    }

    private static boolean gameIsWon() {
        boolean areAllNonMinesVisible = true;
        boolean areAllMinesMarked = true;
        for (int j = 0; j < mineField.length; j++) {
            for (int i = 0; i < mineField[j].length; i++) {
                Cell cell = mineField[j][i];
                if (areAllNonMinesVisible && !cell.isVisible() && !cell.hasMine()) {
                    areAllNonMinesVisible = false;
                }
                if (areAllMinesMarked && cell.hasMine() && !cell.isMarked()) {
                    areAllMinesMarked = false;
                }
                if (!areAllMinesMarked && !areAllNonMinesVisible) {
                    return false;
                }
            }
        }
        return true;
    }

    private void markMines() {
        for (int j = 0; j < mineField.length; j++) {
            for (int i = 0; i < mineField[j].length; i++) {
                if (mineField[j][i].getSymbol() == View.getMINE_CHAR()) {
                    for (int[] neighbour : neighbours) {
                        markCell(j - neighbour[0], i - neighbour[1]);
                    }
                }
            }
        }
    }

    private void markCell(int j, int i) {
        if (j < 0 || j >= mineField.length || i < 0 || i >= mineField[0].length) {
            return;
        }
        Cell cell = mineField[j][i];
        if (cell.isANumber() || cell.isEmpty()) {
            cell.increment();
        }
    }

    private void spreadMines() {
        System.out.print ("How many mines do you want on the field? ");
        int minesToSpread = Integer.parseInt(input.nextLine());
        while (minesToSpread != 0) {
            int i = randomNumber.nextInt(9);
            int j = randomNumber.nextInt(9);
            if (mineField[j][i].getSymbol() != view.getMINE_CHAR()) {
                mineField[j][i].setSymbol(view.getMINE_CHAR());
                minesToSpread--;
            }
        }
    }
}