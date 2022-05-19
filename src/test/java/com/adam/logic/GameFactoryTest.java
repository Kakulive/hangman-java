package com.adam.logic;

import com.adam.logic.words.Category;
import com.adam.logic.words.WordGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameFactoryTest {
    GameFactory gameFactory;
    int easyDifficulty;
    int mediumDifficulty;
    int hardDifficulty;
    int impossibleDifficulty;
    int randomDifficulty;

    WordGenerator wordGenerator = mock(WordGenerator.class);

    @BeforeEach
    void testSetup(){
        this.gameFactory = new GameFactory(wordGenerator);
        this.easyDifficulty = 1;
        this.mediumDifficulty = 2;
        this.hardDifficulty = 3;
        this.impossibleDifficulty = 4;
        when(wordGenerator.getWordsForCategory(any())).thenReturn(Arrays.asList("DOG", "CAT", "PIG"));
    }

    @Test
    void whenCreatingNewGame_ProperDifficultyLevelIsAssigned() {
        //GIVEN
        randomDifficulty = 5;
        //WHEN
        var easyGame = gameFactory.getGame(easyDifficulty);
        var mediumGame = gameFactory.getGame(mediumDifficulty);
        var hardGame = gameFactory.getGame(hardDifficulty);
        var impossibleGame = gameFactory.getGame(impossibleDifficulty);
        var randomGame = gameFactory.getGame(randomDifficulty);
        //THEN
        assertEquals(DifficultyLevel.EASY, easyGame.getDifficulty());
        assertEquals(DifficultyLevel.MEDIUM, mediumGame.getDifficulty());
        assertEquals(DifficultyLevel.HARD, hardGame.getDifficulty());
        assertEquals(DifficultyLevel.IMPOSSIBLE, impossibleGame.getDifficulty());
        assertEquals(DifficultyLevel.EASY, randomGame.getDifficulty());
    }

    @Test
    void whenCreatingNewGame_ProperCategoryIsAssigned() {
        //GIVEN
        var expectedCategories = Arrays.asList(Category.COUNTRIES, Category.CAPITALS, Category.ANIMALS);
        //WHEN
        var game = gameFactory.getGame(easyDifficulty);
        var category = game.getCategory();
        //THEN
        assertNotNull(category);
        assertTrue(expectedCategories.contains(category));
    }

    @Test
    void whenCreatingNewGame_RevealedAndUsedLettersAreEmpty() {
        //GIVEN
        var expectedUsedLetters = Collections.emptyList();
        var expectedRevealedLetters = Collections.emptyList();
        //WHEN
        var game = gameFactory.getGame(easyDifficulty);
        var usedLetters = game.getUsedLetters();
        var revealedLetters = game.getRevealedLetters();
        //THEN
        assertNotNull(usedLetters);
        assertNotNull(revealedLetters);
        assertEquals(expectedUsedLetters, usedLetters);
        assertEquals(expectedRevealedLetters, revealedLetters);

    }

    @Test
    void whenCreatingNewGame_WordToGuessIsProperlyAssigned() {
        //GIVEN
        var possibleExpectedWordToGuess1 = "DOG";
        var possibleExpectedWordToGuess2 = "CAT";
        var possibleExpectedWordToGuess3 = "PIG";
        //WHEN
        var game = gameFactory.getGame(easyDifficulty);
        var wordToGuess = game.getWordToGuess();
        //THEN
        assertNotNull(game.getWordToGuess());
        assertTrue(possibleExpectedWordToGuess1.equals(wordToGuess) ||
                   possibleExpectedWordToGuess2.equals(wordToGuess) ||
                   possibleExpectedWordToGuess3.equals(wordToGuess));
    }

    @Test
    void whenCreatingNewGame_WordToGuessIsProperlySplitToChars() {
        //GIVEN
        var expectedWordToGuessChars = Arrays.asList('D', 'O', 'G');
        when(wordGenerator.getWordsForCategory(any())).thenReturn(List.of("DOG"));
        //WHEN
        var game = gameFactory.getGame(easyDifficulty);
        var wordToGuessChars = game.getWordToGuessChars();
        //THEN
        assertNotNull(wordToGuessChars);
        assertEquals(expectedWordToGuessChars, wordToGuessChars);
    }
}