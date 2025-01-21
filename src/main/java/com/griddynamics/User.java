package com.griddynamics;

import java.util.Scanner;

public final class User implements Player {
    private final Scanner scanner;
    private final Board board;

    public User(final Board board, Scanner scanner) {
        this.board = board;
        this.scanner = scanner;
    }

    @Override
    public int[] getInput() {
        boolean fail;
        int coordinateOne = 0;
        int coordinateTwo = 0;
        do {
            fail = false;
            System.out.print("Enter the coordinates: ");
            final String input = scanner.nextLine();
            final String[] coordinates = input.split(" ");
            if (coordinates.length == 2) {
                coordinateOne = validateInput(coordinates[0]);
                if (coordinateOne == 0) {
                    fail = true;
                    continue;
                }
                coordinateTwo = validateInput(coordinates[1]);
                if (coordinateTwo == 0) {
                    fail = true;
                    continue;
                }

                coordinateOne--;
                coordinateTwo--;
                if (!board.isAvailable(coordinateOne, coordinateTwo)) {
                    System.out.println("This cell is occupied! Choose another one!");
                    fail = true;
                }
            } else if (coordinates.length == 1) {
                coordinateOne = validateInput(coordinates[0]);
                if (coordinateOne != 0) {
                    System.out.println("You need one more number!");
                }
                fail = true;
            } else if (coordinates.length > 2) {
                System.out.println("Too many arguments!");
                fail = true;
            }
        } while (fail);

        return new int[] {coordinateOne, coordinateTwo};
    }
}
