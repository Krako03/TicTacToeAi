package com.griddynamics;

public class Main {


    public static void main(String[] args) {
        String command;
        Board board = new Board();

        while (true) {
            command = Menu.getUserCommand();
            if ("e".equals(command)) {
                break;
            }
            Menu.setGame(command, board);
            board.executeGame();
            board = new Board();
        }
    }
}