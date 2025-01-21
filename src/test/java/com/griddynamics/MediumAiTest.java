package com.griddynamics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MediumAiTest {
    private Board mockBoard;
    private SecureRandom mockRandom;
    private MediumAi mediumAi;

    @BeforeEach
    public void setUp() {
        mockBoard = mock(Board.class);
        mockRandom = mock(SecureRandom.class);
        mediumAi = new MediumAi(mockBoard);
    }

    @Test
    public void testWinningOnFirstSquare() {
        when(mockBoard.checkSquareBW(0,0, false)).thenReturn(true);

        int[] move = mediumAi.getInput();

        assertArrayEquals(new int[]{0, 0}, move);
    }

    @Test
    public void testWinningOnLastSquare() {
        when(mockBoard.checkSquareBW(anyInt(), anyInt(), eq(false))).thenReturn(false);
        when(mockBoard.checkSquareBW(2,2, false)).thenReturn(true);

        int[] move = mediumAi.getInput();

        assertArrayEquals(new int[]{2, 2}, move);
    }

    @Test
    public void testBlockingOnFirstSquare() {
        when(mockBoard.checkSquareBW(anyInt(), anyInt(), eq(false))).thenReturn(false);
        when(mockBoard.checkSquareBW(0, 0, true)).thenReturn(true);

        int[] move = mediumAi.getInput();

        assertArrayEquals(new int[]{0, 0}, move);
    }

    @Test
    public void testBlockingOnLastSquare() {
        when(mockBoard.checkSquareBW(anyInt(), anyInt(), eq(false))).thenReturn(false);
        when(mockBoard.checkSquareBW(anyInt(), anyInt(), eq(true))).thenReturn(false);
        when(mockBoard.checkSquareBW(2, 2, true)).thenReturn(true);

        int[] move = mediumAi.getInput();

        assertArrayEquals(new int[]{2, 2}, move);
    }

    @Test
    public void testGetInputRandomCell() {
        when(mockBoard.checkSquareBW(anyInt(), anyInt(), eq(false))).thenReturn(false);
        when(mockBoard.checkSquareBW(anyInt(), anyInt(), eq(true))).thenReturn(false);
        when(mockRandom.nextInt(3)).thenReturn(0, 0);
        when(mockBoard.isAvailable(0, 0)).thenReturn(true);

        int[] move = mediumAi.getInput();

        assertArrayEquals(new int[]{0, 0}, move);
    }

    @Test
    public void testRetryGetInputRandomCell() {
        when(mockBoard.checkSquareBW(anyInt(), anyInt(), eq(false))).thenReturn(false);
        when(mockBoard.checkSquareBW(anyInt(), anyInt(), eq(true))).thenReturn(false);
        when(mockRandom.nextInt(3)).thenReturn(0, 0, 1, 1);
        when(mockBoard.isAvailable(0, 0)).thenReturn(false);
        when(mockBoard.isAvailable(1, 1)).thenReturn(true);

        int[] move = mediumAi.getInput();

        assertArrayEquals(new int[]{1, 1}, move);
    }
}