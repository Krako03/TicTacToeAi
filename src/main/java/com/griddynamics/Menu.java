package com.griddynamics;

import java.util.Scanner;

public final class Menu {
    private final static String EASY = "easy";
    private final static String MEDIUM = "medium";
    private final static String HARD = "hard";
    private final static String USER = "user";
    private final static String START = "start";
    private final Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getUserCommand() {
        boolean fail;
        boolean hasThree = false;
        String input;
        String command = "";
        String commandOne = "";
        String commandTwo = "";
        String commandThree = "";
        do {
            fail = false;

            System.out.print("Input command: ");

            input = scanner.nextLine();
            final String[] parameters = input.split(" ");

            if (parameters.length == 3) {
                if (START.equals(parameters[0]) && (EASY.equals(parameters[1])
                        || MEDIUM.equals(parameters[1]) || HARD.equals(parameters[1])
                        || USER.equals(parameters[1])) && (EASY.equals(parameters[2])
                        || MEDIUM.equals(parameters[2]) || HARD.equals(parameters[2])
                        || USER.equals(parameters[2]))) {
                    hasThree = true;
                } else {
                    fail = true;
                }
            } else if (parameters.length == 1) {
                if  (!"exit".equals(parameters[0])) {
                    fail = true;
                }
            } else {
                fail = true;
            }

            if (fail) {
                System.out.println("Bad parameters!");
            } else {
                commandOne = parameters[0];
                if (hasThree) {
                    commandTwo = parameters[1];
                    commandThree = parameters[2];
                }
            }

        } while (fail);

        if (START.equals(commandOne)) {
            command += "s";
            switch (commandTwo) {
                case EASY -> command += "e";
                case MEDIUM -> command += "m";
                case HARD -> command += "h";
                case USER -> command += "u";
                default -> command = "x";
            }
            switch (commandThree) {
                case EASY -> command += "e";
                case MEDIUM -> command += "m";
                case HARD -> command += "h";
                case USER -> command += "u";
                default -> command = "x";
            }
        } else {
            command += "e";
        }

        return command;
    }
}
