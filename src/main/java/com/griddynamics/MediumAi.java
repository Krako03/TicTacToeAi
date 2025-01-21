package com.griddynamics;

import java.security.SecureRandom;

public final class MediumAi implements Player {
    private final Board board;
    private final SecureRandom random;

    public MediumAi(final Board board) {
        this.board = board;
        this.random = new SecureRandom();
    }

    @Override
    public int[] getInput() {
        System.out.println("Making move level \"medium\"");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.checkSquareBW(i, j, false)) {
                    return new int[] {i, j};
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.checkSquareBW(i, j, true)) {
                    return new int[] {i, j};
                }
            }
        }

        int coordinateOne;
        int coordinateTwo;
        boolean fail;

        do {
            fail = false;
            coordinateOne = random.nextInt(3);
            coordinateTwo = random.nextInt(3);
            if (!board.isAvailable(coordinateOne, coordinateTwo)) {
                fail = true;
            }
        } while (fail);

        return new int[] {coordinateOne, coordinateTwo};
    }
}
