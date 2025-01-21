package com.griddynamics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    Menu menu;

    @Test
    void testValidThreeWordInput() {
        String simulatedInput = "start easy easy\n";
        Scanner mockScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        menu = new Menu(mockScanner);
        String result = menu.getUserCommand();
        assertEquals("see", result);
    }

    @Test
    void testValidOneWordInput() {
        String simulatedInput = "exit\n";
        Scanner mockScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        menu = new Menu(mockScanner);
        String result = menu.getUserCommand();
        assertEquals("e", result);
    }

    @Test
    void testInvalidThreeWordInput() {
        String simulatedInput = "game easy easy\n" +
                "star hard medium\n" +
                "start user difficult\n" +
                "start medium medium\n";
        Scanner mockScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        menu = new Menu(mockScanner);
        String result = menu.getUserCommand();
        assertEquals("smm", result);
    }

    @Test
    void testInvalidTwoWordInput() {
        String simulatedInput = "start easy\n" +
                "star hard\n" +
                "start user\n" +
                "start hard hard\n";
        Scanner mockScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        menu = new Menu(mockScanner);
        String result = menu.getUserCommand();
        assertEquals("shh", result);
    }

    @Test
    void testInvalidOneWordInput() {
        String simulatedInput = "start\n" +
                "exi\n" +
                "sibfioadod\n" +
                "exit\n";
        Scanner mockScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        menu = new Menu(mockScanner);
        String result = menu.getUserCommand();
        assertEquals("e", result);
    }
}