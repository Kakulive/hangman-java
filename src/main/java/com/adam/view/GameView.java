package com.adam.view;

import com.adam.logic.words.Category;

import java.util.List;

public interface GameView {
    void printMessage(String message);
    void printGuessedWord(String word, List<Character> revealedLetters);
    void printLivesLeft(int remainingLives);
    void printCategory(Category category);
    void wrongLetterMessage();
    void printGameState(int remainingPlayerLives);

}
