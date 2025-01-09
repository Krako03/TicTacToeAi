package com.griddynamics;

import java.util.Random;

public final class EasyAi extends Player {
    private final Board board;

    public EasyAi(final Board board) {
        this.board = board;
    }

    @Override
    public int[] getInput() {
        System.out.println("Making move level \"easy\"");
        final Random random = new Random();
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
