package com.griddynamics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.security.SecureRandom;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserTest {
    User user;
    Scanner mockScanner;
    Board mockBoard;

    @BeforeEach
    public void setUp() {
        mockBoard = mock(Board.class);
    }


    @Test
    void testValidInput() {
        String simulatedInput = "1 1\n";
        mockScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        user = new User(mockBoard, mockScanner);

        when(mockBoard.isAvailable(0, 0)).thenReturn(true);

        int[] move = user.getInput();

        assertArrayEquals(new int[]{0, 0}, move);
    }

    @Test
    void testInvalidInputOneArgument() {
        String simulatedInput = "1\n" +
                "2 2\n";
        mockScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        user = new User(mockBoard, mockScanner);

        when(mockBoard.isAvailable(1, 1)).thenReturn(true);

        int[] move = user.getInput();

        assertArrayEquals(new int[]{1, 1}, move);
    }

    @Test
    void testInvalidInputManyArgument() {
        String simulatedInput = "1 3 1\n" +
                "3 3\n";
        mockScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        user = new User(mockBoard, mockScanner);

        when(mockBoard.isAvailable(2, 2)).thenReturn(true);

        int[] move = user.getInput();

        assertArrayEquals(new int[]{2, 2}, move);
    }

    @Test
    void testInvalidWordArgument() {
        String simulatedInput = "hola pablo\n" +
                "1 3\n";
        mockScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        user = new User(mockBoard, mockScanner);

        when(mockBoard.isAvailable(0, 2)).thenReturn(true);

        int[] move = user.getInput();

        assertArrayEquals(new int[]{0, 2}, move);
    }

    @Test
    void testInvalidOccupiedArgument() {
        String simulatedInput = "3 1\n" +
                "2 1\n";
        mockScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        user = new User(mockBoard, mockScanner);

        when(mockBoard.isAvailable(2, 1)).thenReturn(false);
        when(mockBoard.isAvailable(1, 0)).thenReturn(true);

        int[] move = user.getInput();

        assertArrayEquals(new int[]{1, 0}, move);
    }

    @Test
    void testInvalidOutOfBoundsArgument() {
        String simulatedInput = "4 7\n" +
                "0 5\n" +
                "2 -1\n" +
                "1 3\n";
        mockScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        user = new User(mockBoard, mockScanner);

        when(mockBoard.isAvailable(0, 2)).thenReturn(true);

        int[] move = user.getInput();

        assertArrayEquals(new int[]{0, 2}, move);
    }
}