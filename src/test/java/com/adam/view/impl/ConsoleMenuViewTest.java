package com.adam.view.impl;

import com.adam.view.ConsoleColors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleMenuViewTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final ConsoleMenuView consoleMenuView = new ConsoleMenuView();

    @BeforeEach
    void setup() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void whenPrintWelcomeScreenIsCalled_CorrectMessageIsPrinted() {
        //GIVEN
        String expectedMessage = ConsoleColors.BLUE_BOLD_BRIGHT + "Welcome to HANGMAN" + ConsoleColors.RESET;
        //WHEN
        consoleMenuView.printWelcomeScreen();
        //THEN
        assertEquals(expectedMessage.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void whenPrintDifficultySelectionIsCalled_CorrectMessageIsPrinted() {
        //GIVEN
        String expectedMessage1 = ConsoleColors.GREEN + "Please select the difficulty:" + ConsoleColors.RESET;
        String expectedMessage2 = """
                1 - Default (7 lives)
                2 - Medium (5 lives)
                3 - Hard (3 lives)
                4 - Impossible (1 life)
                Difficulty:
                """;
        //WHEN
        consoleMenuView.difficultySelection();
        //THEN
        assertEquals(expectedMessage1.trim() + System.lineSeparator() + expectedMessage2.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void whenNameSelectionIsCalled_CorrectMessageIsPrinted() {
        //GIVEN
        String expectedMessage = ConsoleColors.GREEN + "Please tell me your name" + ConsoleColors.RESET;
        //WHEN
        consoleMenuView.nameSelection();
        //THEN
        assertEquals(expectedMessage.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void whenPrintEmptyLineIsCalled_EmptyLineGetsPrinted() {
        //GIVEN
        String expectedMessage = "";
        //WHEN
        consoleMenuView.printEmptyLine();
        //THEN
        assertEquals(expectedMessage.trim(), outputStreamCaptor.toString().trim());
    }
}