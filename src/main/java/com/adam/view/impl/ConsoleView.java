package com.adam.view.impl;

import com.adam.helpers.Helper;
import com.adam.logic.words.Category;
import com.adam.view.ConsoleColors;
import com.adam.view.inter.View;

import java.util.List;

public class ConsoleView implements View {
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

    public void printWelcomeScreen() {
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Welcome to HANGMAN" + ConsoleColors.RESET);
    }

    public void printCategory(Category category) {
        System.out.println(ConsoleColors.GREEN + "Category of your word is: " + ConsoleColors.RESET + category.toString());
    }

    public void wrongLetterMessage() {
        System.out.println(ConsoleColors.RED + "Wrong letter, try again!" + ConsoleColors.RESET);
    }

    public void difficultySelection() {
        System.out.println(ConsoleColors.GREEN + "Please select the difficulty:" + ConsoleColors.RESET);
        System.out.println("""
                1 - Default (7 lives)
                2 - Medium (5 lives)
                3 - Hard (3 lives)
                4 - Impossible (1 life)
                Difficulty:
                """);
    }

    public void nameSelection() {
        System.out.println(ConsoleColors.GREEN + "Please tell me your name" + ConsoleColors.RESET);
    }

    public void printGameState(int remainingPlayerLives) {
        if (remainingPlayerLives == 0) {
            System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "GAME OVER" + ConsoleColors.RESET);
        }
        getLivesASCII(remainingPlayerLives);
    }

    public void printEmptyLine() {
        System.out.println();
    }

    public void printVictoryScreen(String word) {
        System.out.println(ConsoleColors.GREEN + "Congratulations! You have won!\nYour word is " + word + ConsoleColors.RESET + "\n");
        List<String> content = helper.readFromFile("victory.txt");
        content.forEach(System.out::println);
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void getLivesASCII(int numberOfLives) {
        List<String> content = helper.readFromFile(numberOfLives + "LivesASCII.txt");
        content.forEach(System.out::println);
    }
}
