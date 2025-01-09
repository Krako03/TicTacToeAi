package com.griddynamics;

public final class Board {
    private char winnerChar = '_';
    private int blanks = 9;
    private boolean xTurn = true;
    private final char[][] grid = {
            {'_', '_', '_'},
            {'_', '_', '_'},
            {'_', '_', '_'}
    };
    private final char BLANK = '_';
    private final char CROSS = 'X';
    private final char CIRCLE = 'O';
    private Player xUser;
    private Player oUser;

    public Board() {
    }

    public void executeGame() {
        printGrid();
        while (true) {
            int[] xInput = xUser.getInput();
            putSquare(xInput[0], xInput[1]);
            printGrid();
            if (checkGame()) {
                printResult();
                break;
            }
            int[] oInput = oUser.getInput();
            putSquare(oInput[0], oInput[1]);
            printGrid();
            if (checkGame()) {
                printResult();
                break;
            }
        }
    }

    public void printResult() {
        if (winnerChar == BLANK) {
            System.out.println("Draw");
        } else if (winnerChar == CROSS) {
            System.out.println("X wins");
        } else if (winnerChar == CIRCLE) {
            System.out.println("O wins");
        }
    }

    public void putSquare(final int coordinateOne, final int coordinateTwo) {
        if (xTurn) {
            grid[coordinateOne][coordinateTwo] = 'X';
            blanks--;
            xTurn = false;
        } else {
            grid[coordinateOne][coordinateTwo] = 'O';
            blanks--;
            xTurn = true;
        }
    }

    public void putSquareBack(final int coordinateOne, final int coordinateTwo, final boolean isBlocking) {
        grid[coordinateOne][coordinateTwo] = '_';
        blanks++;
        if (!isBlocking) {
            xTurn = !xTurn;
        }
    }

    public boolean isAvailable(final int coordinateOne, final int coordinateTwo) {
        return grid[coordinateOne][coordinateTwo] == BLANK;
    }

    public void setPlayers(final Player playerOne, final Player playerTwo) {
        xUser = playerOne;
        oUser = playerTwo;
    }

    private void printGrid() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == BLANK) {
                    System.out.print(" ");
                } else {
                    System.out.print(grid[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public char getWinnerChar() {
        return winnerChar;
    }

    public boolean checkSquareBW(final int coordinateOne, final int coordinateTwo, final boolean isBlocking) {
        boolean flag;
        if (isAvailable(coordinateOne, coordinateTwo)) {
            if (isBlocking) {
                xTurn = !xTurn;
            }
            putSquare(coordinateOne, coordinateTwo);
            if (checkGame()) {
                flag = true;
            } else {
                flag = false;
            }
            putSquareBack(coordinateOne, coordinateTwo, isBlocking);
        } else {
            flag = false;
        }
        return flag;
    }

    public boolean checkGame() {
        boolean winner = true;
        char currentValue;

        for (int i = 0; i < 3; i++) {
            winner = true;
            currentValue = grid[i][0];
            if (currentValue == BLANK) {
                winner = false;
                continue;
            }
            for (int j = 1; j < 3; j++) {
                if (currentValue != grid[i][j]) {
                    winner = false;
                    break;
                }
            }
            if (winner) {
                winnerChar = currentValue;
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            winner = true;
            currentValue = grid[0][i];
            if (currentValue == BLANK) {
                winner = false;
                continue;
            }
            for (int j = 1; j < 3; j++) {
                if (currentValue != grid[j][i]) {
                    winner = false;
                    break;
                }
            }
            if (winner) {
                winnerChar = currentValue;
                return true;
            }
        }

        currentValue = grid[0][0];
        if (currentValue != BLANK) {
            winner = true;
            for (int i = 1; i < 3; i++) {
                if (currentValue != grid[i][i]) {
                    winner = false;
                    break;
                }
            }
        }

        if (winner) {
            winnerChar = currentValue;
            return true;
        }

        currentValue = grid[0][2];
        if (currentValue != BLANK) {
            winner = true;
            int j = 1;
            for (int i = 1; i < 3; i++) {
                if (currentValue != grid[i][j]) {
                    winner = false;
                    break;
                }
                j--;
            }
        }

        if (winner) {
            winnerChar = currentValue;
            return true;
        }

        if (blanks == 0) {
            winnerChar = '_';
            return true;
        }

        return false;
    }

    public boolean isxTurn() {
        return xTurn;
    }
}
