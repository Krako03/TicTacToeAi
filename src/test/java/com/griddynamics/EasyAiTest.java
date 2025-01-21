package com.griddynamics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EasyAiTest {
    private Board mockBoard;
    private SecureRandom mockRandom;
    private EasyAi easyAi;

    @BeforeEach
    public void setUp() {
        mockBoard = mock(Board.class);
        mockRandom = mock(SecureRandom.class);
        easyAi = new EasyAi(mockBoard);
    }

    @Test
    public void testGetInput_ValidMove() {
        when(mockRandom.nextInt(3)).thenReturn(0, 1); // First call (0, 1)
        when(mockBoard.isAvailable(0, 1)).thenReturn(true); // (0, 1) is available

        int[] move = easyAi.getInput();

        assertArrayEquals(new int[]{0, 1}, move);
    }

    @Test
    public void testGetInput_RetryOnOccupiedCell() {
        when(mockRandom.nextInt(3)).thenReturn(0, 1, 2, 2); // First (0, 1), retry (2, 2)
        when(mockBoard.isAvailable(0, 1)).thenReturn(false); // (0, 1) is occupied
        when(mockBoard.isAvailable(2, 2)).thenReturn(true); // (2, 2) is available

        int[] move = easyAi.getInput();

        assertArrayEquals(new int[]{2, 2}, move);
    }
}
