package com.adam.logic;

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

class GameTest {
    GameFactory gameFactory;
    int easyDifficulty;

    WordGenerator wordGenerator = mock(WordGenerator.class);

    @BeforeEach
    void testSetup(){
        this.gameFactory = new GameFactory(wordGenerator);
        this.easyDifficulty = 1;
        when(wordGenerator.getWordsForCategory(any())).thenReturn(Arrays.asList("DOG", "CAT", "PIG"));
    }

    @Test
    void whenCorrectLetterIsGuessed_LetterGetsAddedToUsedAndRevealedLetters() {
        //GIVEN
        List<Character> expectedUsedLetters = List.of('C');
        List<Character> expectedRevealedLetters = List.of('C');
        //WHEN
        Game game = gameFactory.getGame(easyDifficulty);
        game.correctLetterGuessedUpdate('C');
        List<Character> usedLetters = game.getUsedLetters();
        List<Character> revealedLetters = game.getRevealedLetters();
        //THEN
        assertEquals(expectedUsedLetters, usedLetters);
        assertEquals(expectedRevealedLetters, revealedLetters);
    }

    @Test
    void whenWrongLetterIsGuessed_LetterGetsAddedToUsedLettersButNotToRevealedLetters() {
        //GIVEN
        List<Character> expectedUsedLetters = List.of('D');
        List<Character> expectedRevealedLetters = Collections.emptyList();
        //WHEN
        Game game = gameFactory.getGame(easyDifficulty);
        game.wrongLetterGuessedUpdate('D');
        List<Character> usedLetters = game.getUsedLetters();
        List<Character> revealedLetters = game.getRevealedLetters();
        //THEN
        assertEquals(expectedUsedLetters, usedLetters);
        assertEquals(expectedRevealedLetters, revealedLetters);
    }

    @Test
    void whenPlayerGuessedEntireWord_PlayerHasWon() {
        //GIVEN
        List<Character> revealedLetters = Arrays.asList('D', 'O', 'G');
        //WHEN
        Game game = gameFactory.getGame(easyDifficulty);
        game.setWordToGuessChars(revealedLetters);
        game.setRevealedLetters(revealedLetters);
        boolean hasPlayerWon = game.hasPlayerWon();
        //THEN
        assertTrue(hasPlayerWon);
    }

    @Test
    void whenPlayerHasNotGuessedEntireWord_PlayerHasNotWon() {
        //GIVEN
        List<Character> revealedLetters = Arrays.asList('D', 'O');
        //WHEN
        Game game = gameFactory.getGame(easyDifficulty);
        game.setWordToGuessChars(Arrays.asList('D', 'O', 'G'));
        game.setRevealedLetters(revealedLetters);
        boolean hasPlayerWon = game.hasPlayerWon();
        //THEN
        assertFalse(hasPlayerWon);
    }
}