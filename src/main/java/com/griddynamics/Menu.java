package com.griddynamics;

import java.util.Scanner;

public class Menu {
    private static final String EASY = "easy";
    private static final String MEDIUM = "medium";
    private static final String HARD = "hard";
    private static final String USER = "user";
    private static final String START = "start";
    private static Scanner scanner = new Scanner(System.in);

    public static String getUserCommand() {
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
                if ((START.equals(parameters[0])) && (EASY.equals(parameters[1])
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

    public static void setGame(final String command, final Board board) {
        switch (command) {
            case "e" -> { }
            case "suu" -> {
                Player xPlayer = new User(board);
                Player oPlayer = new User(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            case "sue" -> {
                Player xPlayer = new User(board);
                Player oPlayer = new EasyAi(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            case "sum" -> {
                Player xPlayer = new User(board);
                Player oPlayer = new MediumAi(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            case "suh" -> {
                Player xPlayer = new User(board);
                Player oPlayer = new HardAi(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            case "seu" -> {
                Player xPlayer = new EasyAi(board);
                Player oPlayer = new User(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            case "smu" -> {
                Player xPlayer = new MediumAi(board);
                Player oPlayer = new User(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            case "shu" -> {
                Player xPlayer = new HardAi(board);
                Player oPlayer = new User(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            case "see" -> {
                Player xPlayer = new EasyAi(board);
                Player oPlayer = new EasyAi(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            case "sem" -> {
                Player xPlayer = new EasyAi(board);
                Player oPlayer = new MediumAi(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            case "seh" -> {
                Player xPlayer = new EasyAi(board);
                Player oPlayer = new HardAi(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            case "sme" -> {
                Player xPlayer = new MediumAi(board);
                Player oPlayer = new EasyAi(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            case "she" -> {
                Player xPlayer = new HardAi(board);
                Player oPlayer = new EasyAi(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            case "smm" -> {
                Player xPlayer = new MediumAi(board);
                Player oPlayer = new MediumAi(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            case "smh" -> {
                Player xPlayer = new MediumAi(board);
                Player oPlayer = new HardAi(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            case "shm" -> {
                Player xPlayer = new HardAi(board);
                Player oPlayer = new MediumAi(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            case "shh" -> {
                Player xPlayer = new HardAi(board);
                Player oPlayer = new HardAi(board);
                board.setPlayers(xPlayer, oPlayer);
            }
            default -> System.out.println("Place a proper command!");
        }
    }
}
