package com.griddynamics;

import java.util.Random;

public class MediumAi extends Player{
    private final Board board;

    public MediumAi(Board board){
        this.board = board;
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
