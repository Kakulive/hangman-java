package com.adam.view.impl;

import com.adam.helpers.Helper;
import com.adam.logic.words.Category;
import com.adam.view.ConsoleColors;
import com.adam.view.GameView;

import java.util.List;

public class ConsoleGameView implements GameView {
    private final Helper helper = new Helper();

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printGuessedWord(String word, List<Character> revealedLetters) {
        StringBuilder sb = new StringBuilder();
        sb.append(ConsoleColors.GREEN + "Word to guess: " + ConsoleColors.RESET);
        char[] wordChars = word.toCharArray();
        for (char c : wordChars) {
            if (revealedLetters.contains(c)) {
                sb.append(c).append(" ");
            } else if (c == ' ') {
                sb.append("   ");
            } else {
                sb.append("_ ");
            }
        }
        System.out.println(sb);
    }

    public void printLivesLeft(int remainingLives) {
        System.out.println(ConsoleColors.GREEN + "LIVES: " + ConsoleColors.RESET + remainingLives);
    }

    public void printCategory(Category category) {
        System.out.println(ConsoleColors.GREEN + "Category of your word is: " + ConsoleColors.RESET + category.toString());
    }

    public void wrongLetterMessage() {
        System.out.println(ConsoleColors.RED + "Wrong letter, try again!" + ConsoleColors.RESET);
    }

    public void printGameState(int remainingPlayerLives) {
        if (remainingPlayerLives == 0) {
            System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "GAME OVER" + ConsoleColors.RESET);
        }
        getLivesASCII(remainingPlayerLives);
    }

    private void getLivesASCII(int numberOfLives) {
        List<String> content = helper.readFromFile(numberOfLives + "LivesASCII.txt");
        content.forEach(System.out::println);
    }
}
