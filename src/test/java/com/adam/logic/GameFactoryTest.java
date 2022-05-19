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
        Game easyGame = gameFactory.getGame(easyDifficulty);
        Game mediumGame = gameFactory.getGame(mediumDifficulty);
        Game hardGame = gameFactory.getGame(hardDifficulty);
        Game impossibleGame = gameFactory.getGame(impossibleDifficulty);
        Game randomGame = gameFactory.getGame(randomDifficulty);
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
        List<Category> expectedCategories = Arrays.asList(Category.COUNTRIES, Category.CAPITALS, Category.ANIMALS);
        //WHEN
        Game game = gameFactory.getGame(easyDifficulty);
        Category category = game.getCategory();
        //THEN
        assertNotNull(category);
        assertTrue(expectedCategories.contains(category));
    }

    @Test
    void whenCreatingNewGame_RevealedAndUsedLettersAreEmpty() {
        //GIVEN
        List<Character> expectedUsedLetters = Collections.emptyList();
        List<Character> expectedRevealedLetters = Collections.emptyList();
        //WHEN
        Game game = gameFactory.getGame(easyDifficulty);
        List<Character> usedLetters = game.getUsedLetters();
        List<Character> revealedLetters = game.getRevealedLetters();
        //THEN
        assertNotNull(usedLetters);
        assertNotNull(revealedLetters);
        assertEquals(expectedUsedLetters, usedLetters);
        assertEquals(expectedRevealedLetters, revealedLetters);

    }

    @Test
    void whenCreatingNewGame_WordToGuessIsProperlyAssigned() {
        //GIVEN
        String possibleExpectedWordToGuess1 = "DOG";
        String possibleExpectedWordToGuess2 = "CAT";
        String possibleExpectedWordToGuess3 = "PIG";
        //WHEN
        Game game = gameFactory.getGame(easyDifficulty);
        String wordToGuess = game.getWordToGuess();
        //THEN
        assertNotNull(game.getWordToGuess());
        assertTrue(possibleExpectedWordToGuess1.equals(wordToGuess) ||
                   possibleExpectedWordToGuess2.equals(wordToGuess) ||
                   possibleExpectedWordToGuess3.equals(wordToGuess));
    }

    @Test
    void whenCreatingNewGame_WordToGuessIsProperlySplitToChars() {
        //GIVEN
        List<Character> expectedWordToGuessChars = Arrays.asList('D', 'O', 'G');
        when(wordGenerator.getWordsForCategory(any())).thenReturn(List.of("DOG"));
        //WHEN
        Game game = gameFactory.getGame(easyDifficulty);
        List<Character> wordToGuessChars = game.getWordToGuessChars();
        //THEN
        assertNotNull(wordToGuessChars);
        assertEquals(expectedWordToGuessChars, wordToGuessChars);
    }
}