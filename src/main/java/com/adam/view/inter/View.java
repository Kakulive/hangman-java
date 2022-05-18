package com.adam.view.inter;

import com.adam.logic.words.Category;

import java.util.List;

public interface View {
    void printMessage(String message);
    void printGuessedWord(String word, List<Character> revealedLetters);
    void printLivesLeft(int remainingLives);
    void printWelcomeScreen();
    void printCategory(Category category);
    void wrongLetterMessage();
    void difficultySelection();
    void nameSelection();
    void printGameState(int remainingPlayerLives);
    void printEmptyLine();
    void printVictoryScreen(String word);
    void clearScreen();
}
