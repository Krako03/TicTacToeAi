package com.griddynamics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardTest {
    Board board;
    Player xPlayer;
    Player oPlayer;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testMakeMove_ValidMove() {
        board.putSquare(0,0); // Place X on the top-left
        assertEquals('X', board.getCell(0,0)); // Check the cell was updated
    }

    @Test
    public void testMakeMove_InvalidMove() {
        board.putSquare(0,0); // Place X on the top-left
        assertFalse(board.isAvailable(0,0)); // Try to place O in the same cell
    }

    @Test
    public void testTurnAlternation() {
        board.putSquare(0, 0); // X's turn
        board.putSquare(1, 0); // O's turn
        assertEquals('O', board.getCell(1, 0)); // Check O's mark
    }

    @Test
    public void testWinningCondition_Row() {
        board.putSquare(0, 0); // X
        board.putSquare(1, 0); // O
        board.putSquare(0, 1); // X
        board.putSquare(1, 1); // O
        board.putSquare(0, 2); // X wins
        assertTrue(board.checkGame());
        assertEquals('X', board.getWinnerChar());
    }

    @Test
    public void testWinningCondition_Column() {
        board.putSquare(0, 0); // X
        board.putSquare(0, 1); // O
        board.putSquare(1, 0); // X
        board.putSquare(2, 2); // O
        board.putSquare(2, 0); // X wins
        assertTrue(board.checkGame());
        assertEquals('X', board.getWinnerChar());
    }

    @Test
    public void testWinningCondition_Cross() {
        board.putSquare(0, 0); // X
        board.putSquare(0, 1); // O
        board.putSquare(1, 1); // X
        board.putSquare(2, 1); // O
        board.putSquare(2, 2); // X wins
        assertTrue(board.checkGame());
        assertEquals('X', board.getWinnerChar());
    }

    @Test
    public void testDrawCondition() {
        board.putSquare(0, 0); // X
        board.putSquare(0, 1); // O
        board.putSquare(0, 2); // X
        board.putSquare(1, 1); // O
        board.putSquare(1, 0); // X
        board.putSquare(1, 2); // O
        board.putSquare(2, 1); // X
        board.putSquare(2, 0); // O
        board.putSquare(2, 2); // X - Board full
        assertTrue(board.checkGame());
        assertEquals('_', board.getWinnerChar());
    }

    @Test
    public void testUnfinishedCondition() {
        board.putSquare(0, 0); // X
        board.putSquare(0, 1); // O
        board.putSquare(0, 2); // X - Game unfinished
        assertFalse(board.checkGame());
        assertEquals('\0', board.getWinnerChar());
    }

    @Test
    public void testGameReset() {
        board.putSquare(0, 0); // Place X
        board.restartGame(); // Reset
        assertTrue(board.isAvailable(0, 0)); // Board should be cleared
        assertTrue(board.isxTurn()); // Reset to initial player
    }

    @Test
    public void testPuttingSquareBack() {
        board.putSquare(0, 0); // Place X
        board.putSquareBack(0,0, false); // Reset
        assertEquals('_', board.getCell(0, 0)); // Board should be cleared
        assertTrue(board.isxTurn()); // Reset to initial player
    }

    @Test
    public void testSimulatedGameXWins() {
        xPlayer = mock(Player.class);
        oPlayer = mock(Player.class);
        when(xPlayer.getInput()).then(new Answer<int[]>() {
            int i = 0;
            int j = 0;

            @Override
            public int[] answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new int[] {i++, j};
            }
        });

        when(oPlayer.getInput()).then(new Answer<int[]>() {
            int i = 0;
            int j = 1;

            @Override
            public int[] answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new int[] {i++, j};
            }
        });

        board.setPlayers(xPlayer, oPlayer);
        board.executeGame();

        assertTrue(board.checkGame());
        assertEquals('X', board.getWinnerChar());
    }

    @Test
    public void testSimulatedGameOWins() {
        xPlayer = mock(Player.class);
        oPlayer = mock(Player.class);
        when(xPlayer.getInput()).then(new Answer<int[]>() {
            int i = 0;
            int j = 0;

            @Override
            public int[] answer(InvocationOnMock invocationOnMock) throws Throwable {
                if (i == 2) {
                    i = 0;
                    j = 2;
                }
                return new int[] {i++, j};
            }
        });

        when(oPlayer.getInput()).then(new Answer<int[]>() {
            int i = 0;
            int j = 1;

            @Override
            public int[] answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new int[] {i++, j};
            }
        });

        board.setPlayers(xPlayer, oPlayer);
        board.executeGame();

        assertTrue(board.checkGame());
        assertEquals('O', board.getWinnerChar());
    }

    @Test
    void testCheckWinning() {
        board.putSquare(0, 0); // X
        board.putSquare(0, 1); // O
        board.putSquare(1, 0); // X
        board.putSquare(1, 1); // O
        assertTrue(board.checkSquareBW(2, 0, false));
        assertFalse(board.checkSquareBW(2, 2, false));
    }

    @Test
    void testCheckBlocking() {
        board.putSquare(0, 0); // X
        board.putSquare(0, 1); // O
        board.putSquare(1, 0); // X
        assertTrue(board.checkSquareBW(2, 0, true));
        assertFalse(board.checkSquareBW(2, 2, true));
    }

    @Nested
    class SetGame {
        @Test
        void testSetGameUU() {
            board.setGame("suu");
            assertEquals(User.class, board.getXUser().getClass());
            assertEquals(User.class, board.getOUser().getClass());
        }

        @Test
        void testSetGameUE() {
            board.setGame("sue");
            assertEquals(User.class, board.getXUser().getClass());
            assertEquals(EasyAi.class, board.getOUser().getClass());
        }

        @Test
        void testSetGameUM() {
            board.setGame("sum");
            assertEquals(User.class, board.getXUser().getClass());
            assertEquals(MediumAi.class, board.getOUser().getClass());
        }

        @Test
        void testSetGameUH() {
            board.setGame("suh");
            assertEquals(User.class, board.getXUser().getClass());
            assertEquals(HardAi.class, board.getOUser().getClass());
        }

        @Test
        void testSetGameEU() {
            board.setGame("seu");
            assertEquals(EasyAi.class, board.getXUser().getClass());
            assertEquals(User.class, board.getOUser().getClass());
        }

        @Test
        void testSetGameEE() {
            board.setGame("see");
            assertEquals(EasyAi.class, board.getXUser().getClass());
            assertEquals(EasyAi.class, board.getOUser().getClass());
        }

        @Test
        void testSetGameEM() {
            board.setGame("sem");
            assertEquals(EasyAi.class, board.getXUser().getClass());
            assertEquals(MediumAi.class, board.getOUser().getClass());
        }

        @Test
        void testSetGameEH() {
            board.setGame("seh");
            assertEquals(EasyAi.class, board.getXUser().getClass());
            assertEquals(HardAi.class, board.getOUser().getClass());
        }

        @Test
        void testSetGameMU() {
            board.setGame("smu");
            assertEquals(MediumAi.class, board.getXUser().getClass());
            assertEquals(User.class, board.getOUser().getClass());
        }

        @Test
        void testSetGameME() {
            board.setGame("sme");
            assertEquals(MediumAi.class, board.getXUser().getClass());
            assertEquals(EasyAi.class, board.getOUser().getClass());
        }

        @Test
        void testSetGameMM() {
            board.setGame("smm");
            assertEquals(MediumAi.class, board.getXUser().getClass());
            assertEquals(MediumAi.class, board.getOUser().getClass());
        }

        @Test
        void testSetGameMH() {
            board.setGame("smh");
            assertEquals(MediumAi.class, board.getXUser().getClass());
            assertEquals(HardAi.class, board.getOUser().getClass());
        }

        @Test
        void testSetGameHU() {
            board.setGame("shu");
            assertEquals(HardAi.class, board.getXUser().getClass());
            assertEquals(User.class, board.getOUser().getClass());
        }

        @Test
        void testSetGameHE() {
            board.setGame("she");
            assertEquals(HardAi.class, board.getXUser().getClass());
            assertEquals(EasyAi.class, board.getOUser().getClass());
        }

        @Test
        void testSetGameHM() {
            board.setGame("shm");
            assertEquals(HardAi.class, board.getXUser().getClass());
            assertEquals(MediumAi.class, board.getOUser().getClass());
        }

        @Test
        void testSetGameHH() {
            board.setGame("shh");
            assertEquals(HardAi.class, board.getXUser().getClass());
            assertEquals(HardAi.class, board.getOUser().getClass());
        }
    }
}