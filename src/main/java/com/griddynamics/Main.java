package com.griddynamics;

import java.util.Scanner;

public class Main {

    public static void main(final String[] args) {
        String command;
        final Scanner scanner = new Scanner(System.in);
        final Board board = new Board();
        final Menu menu = new Menu(scanner);

        while (true) {
            command = menu.getUserCommand();
            if ("e".equals(command)) {
                board.closeScanner();
                break;
            }
            board.setGame(command);
            board.executeGame();
            board.restartGame();
        }

        scanner.close();
    }
}
