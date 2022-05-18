package com.adam.view.impl;

import com.adam.logic.words.Category;
import com.adam.view.ConsoleColors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleGameViewTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final ConsoleGameView consoleGameView = new ConsoleGameView();

    @BeforeEach
    void setup() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void whenPrintMessageIsCalled_TheExactMessageGetsPrinted() {
        //GIVEN
        String expectedMessage = "Hello World";
        //WHEN
        consoleGameView.printMessage(expectedMessage);
        //THEN
        assertEquals(expectedMessage, outputStreamCaptor.toString().trim());
    }

    @Test
    void whenSomeCharactersAreRevealed_thenGuessedWordPrintsWithCharactersAndDashes() {
        //GIVEN
        String word = "BARCELONA";
        List<Character> revealedLetters = Arrays.asList('B', 'R', 'C');
        String expectedMessage = ConsoleColors.GREEN + "Word to guess: " + ConsoleColors.RESET + "B _ R C _ _ _ _ _";
        //WHEN
        consoleGameView.printGuessedWord(word, revealedLetters);
        //THEN
        assertEquals(expectedMessage.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void whenPrintLivesLeftIsCalled_AmountOfLivesIsPrinted() {
        //GIVEN
        String expectedMessage = ConsoleColors.GREEN + "LIVES: " + ConsoleColors.RESET + "5";
        //WHEN
        consoleGameView.printLivesLeft(5);
        //THEN
        assertEquals(expectedMessage.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void whenPrintCategoryIsCalled_CategoryIsPrinted() {
        //GIVEN
        String expectedMessage = ConsoleColors.GREEN + "Category of your word is: " + ConsoleColors.RESET + "CAPITALS";
        //WHEN
        consoleGameView.printCategory(Category.CAPITALS);
        //THEN
        assertEquals(expectedMessage.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void whenWrongLetterMessageIsCalled_CorrectMessageIsPrinted() {
        //GIVEN
        String expectedMessage = ConsoleColors.RED + "Wrong letter, try again!" + ConsoleColors.RESET;
        //WHEN
        consoleGameView.wrongLetterMessage();
        //THEN
        assertEquals(expectedMessage.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void whenPrintGameStateWith0LivesIsCalled_GameOverMessageAppears() {
        //GIVEN
        String expectedMessage = ConsoleColors.RED_BACKGROUND_BRIGHT + "GAME OVER" + ConsoleColors.RESET +
                                 "\n                 +---+\n" +
                                 "                 |   |\n" +
                                 "                 O   |\n" +
                                 "                /|\\  |\n" +
                                 "                / \\  |\n" +
                                 "                     |\n" +
                                 "                 =========";
        //WHEN
        consoleGameView.printGameState(0);
        //THEN
        assertEquals(expectedMessage.trim().replaceAll("\n", "").replaceAll("\r", ""),
                outputStreamCaptor.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    }

    @Test
    void whenPrintGameStateWhileAlive_ProperHangmanAppears() {
        //GIVEN
        String expectedMessage = "                 +---+\n" +
                                 "                 |   |\n" +
                                 "                 O   |\n" +
                                 "                /|\\  |\n" +
                                 "                /    |\n" +
                                 "                     |\n" +
                                 "                 =========";
        //WHEN
        consoleGameView.printGameState(1);
        //THEN
        assertEquals(expectedMessage.trim().replaceAll("\n", "").replaceAll("\r", ""),
                outputStreamCaptor.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    }


}