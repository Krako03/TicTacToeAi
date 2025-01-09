package com.griddynamics;

public final class HardAi extends Player {
    private final Board board;

    public HardAi(final Board board) {
        this.board = board;
    }

    @Override
    public int[] getInput() {
        System.out.println("Making move level \"hard\"");
        int bestScore;

        if (board.isxTurn()) {
            bestScore = Integer.MIN_VALUE;
        } else {
            bestScore = Integer.MAX_VALUE;
        }

        int coordinateOne = 0;
        int coordinateTwo = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isAvailable(i, j)) {
                    board.putSquare(i, j);
                    final int score = minimax(board.isxTurn());
                    board.putSquareBack(i, j, false);
                    if (board.isxTurn()) {
                        if (bestScore < score) {
                            bestScore = score;
                            coordinateOne = i;
                            coordinateTwo = j;
                        }
                    } else {
                        if (bestScore > score) {
                            bestScore = score;
                            coordinateOne = i;
                            coordinateTwo = j;
                        }
                    }
                }
            }
        }

        return new int[] {coordinateOne, coordinateTwo};
    }

    private int minimax(final boolean isMaximizing) {
        int score;
        if (board.checkGame()) {
            switch (board.getWinnerChar()) {
                case 'X' -> score = 1;
                case 'O' -> score = -1;
                default -> score = 0;
            }
            return score;
        }

        int bestScore;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
        } else {
            bestScore = Integer.MAX_VALUE;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isAvailable(i, j)) {
                    board.putSquare(i, j);
                    score = minimax(board.isxTurn());
                    board.putSquareBack(i, j, false);
                    if (isMaximizing) {
                        bestScore = Math.max(score, bestScore);
                    } else {
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }
}
