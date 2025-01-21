package com.griddynamics;

import java.security.SecureRandom;

public final class EasyAi implements Player {
    private final Board board;
    private final SecureRandom random;

    public EasyAi(final Board board) {
        this.board = board;
        this.random = new SecureRandom();
    }

    @Override
    public int[] getInput() {
        System.out.println("Making move level \"easy\"");
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
