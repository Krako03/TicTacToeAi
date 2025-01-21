package com.griddynamics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HardAiTest {
    HardAi hardAi;
    Board mockBoard;
    char[][] boardState;
    AtomicBoolean xTurn;

    @BeforeEach
    void setUp() {
        mockBoard = mock(Board.class);
        hardAi = new HardAi(mockBoard);
        xTurn = new AtomicBoolean(true);

        boardState = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardState[i][j] = ' ';
            }
        }

        when(mockBoard.isAvailable(anyInt(), anyInt())).thenAnswer(invocation -> {
            int row = invocation.getArgument(0);
            int col = invocation.getArgument(1);
            return boardState[row][col] == ' ';
        });

        doAnswer(invocation -> {
            int row = invocation.getArgument(0);
            int col = invocation.getArgument(1);
            boardState[row][col] = xTurn.get() ? 'X' : 'O';;
            xTurn.set(!xTurn.get());
            return null;
        }).when(mockBoard).putSquare(anyInt(), anyInt());

        doAnswer(invocation -> {
            int row = invocation.getArgument(0);
            int col = invocation.getArgument(1);
            boardState[row][col] = ' ';
            xTurn.set(!xTurn.get());
            return null;
        }).when(mockBoard).putSquareBack(anyInt(), anyInt(), anyBoolean());

        when(mockBoard.isxTurn()).thenAnswer(invocation -> xTurn.get());

        when(mockBoard.checkGame()).thenAnswer(invocation -> isGameOver());

        when(mockBoard.getWinnerChar()).thenAnswer(invocation -> getWinner());
    }

    @Test
    void testInputEmptyGrid() {
        int[] move = hardAi.getInput();

        assertArrayEquals(new int[]{0, 0}, move);
    }

    @Test
    void testInputCustomGridXCase() {
        boardState[0][0] = 'X';
        boardState[0][1] = 'O';
        boardState[0][2] = 'O';
        boardState[1][0] = 'O';
        boardState[1][1] = 'X';
        boardState[1][2] = 'X';

        int[] move = hardAi.getInput();

        assertArrayEquals(new int[]{2, 2}, move);
    }

    @Test
    void testInputCustomGridOCase() {
        boardState[0][0] = 'X';
        boardState[0][1] = 'O';
        boardState[0][2] = 'X';
        boardState[1][1] = 'X';
        boardState[2][0] = 'O';
        xTurn.set(false);

        int[] move = hardAi.getInput();

        assertArrayEquals(new int[]{2, 2}, move);
    }

    private boolean isGameOver() {
        return getWinner() != ' ' || isBoardFull();
    }

    private char getWinner() {
        for (int i = 0; i < 3; i++) {
            if (boardState[i][0] != ' ' && boardState[i][0] == boardState[i][1] && boardState[i][1] == boardState[i][2]) {
                return boardState[i][0];
            }
            if (boardState[0][i] != ' ' && boardState[0][i] == boardState[1][i] && boardState[1][i] == boardState[2][i]) {
                return boardState[0][i];
            }
        }
        if (boardState[0][0] != ' ' && boardState[0][0] == boardState[1][1] && boardState[1][1] == boardState[2][2]) {
            return boardState[0][0];
        }
        if (boardState[0][2] != ' ' && boardState[0][2] == boardState[1][1] && boardState[1][1] == boardState[2][0]) {
            return boardState[0][2];
        }
        return ' ';
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardState[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}