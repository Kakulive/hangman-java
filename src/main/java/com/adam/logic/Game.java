package com.adam.logic;

import com.adam.logic.words.Category;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
public class Game {
    private DifficultyLevel difficulty;
    private String wordToGuess;
    private Category category;
    private List<Character> wordToGuessChars;
    private List<Character> usedLetters;
    private List<Character> revealedLetters;

    public Game(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    public void correctLetterGuessedUpdate(char guessedLetter) {
        usedLetters.add(guessedLetter);
        revealedLetters.add(guessedLetter);
    }

    public void wrongLetterGuessedUpdate(char guessedLetter) {
        usedLetters.add(guessedLetter);
    }

    public boolean hasPlayerWon() {
        for (char c : wordToGuessChars) {
            if (c == ' ') {
                continue;
            }
            if (!revealedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }
}
