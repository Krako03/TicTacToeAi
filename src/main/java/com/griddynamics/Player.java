package com.griddynamics;

public abstract class Player {
    public abstract int[] getInput();

    public int validateInput(final String coordinateToValidate) {
        final int coordinate;
        try {
            coordinate = Integer.parseInt(coordinateToValidate);
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return 0;
        }

        if (coordinate < 1 || coordinate > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return 0;
        }

        return coordinate;
    }
}
