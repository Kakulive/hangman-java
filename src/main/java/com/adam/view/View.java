package com.adam.view;

import com.adam.logic.words.Category;

import java.util.List;

public class View {
    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printGuessedWord(String word, List<Character> revealedLetters) {
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

    public static void printLivesLeft(int remainingLives) {
        System.out.println(ConsoleColors.GREEN + "LIVES: " + ConsoleColors.RESET + remainingLives);
    }

    public static void printWelcomeScreen() {
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Welcome to HANGMAN" + ConsoleColors.RESET);
    }

    public static void printCategory(Category category) {
        System.out.println(ConsoleColors.GREEN + "Category of your word is: " + ConsoleColors.RESET + category.toString());
    }

    public static void wrongLetterMessage() {
        System.out.println(ConsoleColors.RED + "Wrong letter, try again!" + ConsoleColors.RESET);
    }

    public static void difficultySelection() {
        System.out.println(ConsoleColors.GREEN + "Please select the difficulty:" + ConsoleColors.RESET);
        System.out.println("""
                1 - Default (7 lives)
                2 - Medium (5 lives)
                3 - Hard (3 lives)
                4 - Impossible (1 life)
                Difficulty:
                """);
    }

    public static void nameSelection() {
        System.out.println(ConsoleColors.GREEN + "Please tell me your name" + ConsoleColors.RESET);
    }

    public static void printGameState(int remainingPlayerLives) {
        switch (remainingPlayerLives) {
            case 7 -> get7LivesASCII();
            case 6 -> get6LivesASCII();
            case 5 -> get5LivesASCII();
            case 4 -> get4LivesASCII();
            case 3 -> get3LivesASCII();
            case 2 -> get2LivesASCII();
            case 1 -> get1LivesASCII();
            case 0 -> get0LivesASCII();
            default -> throw new IllegalStateException("Cannot read game state");
        }
    }

    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printVictoryScreen(String word) {
        System.out.println(ConsoleColors.GREEN + "Congratulations! You have won!\nYour word is " + word + ConsoleColors.RESET + "\n");
        System.out.println("""

                ██╗░░░██╗██╗░█████╗░████████╗░█████╗░██████╗░██╗░░░██╗
                ██║░░░██║██║██╔══██╗╚══██╔══╝██╔══██╗██╔══██╗╚██╗░██╔╝
                ╚██╗░██╔╝██║██║░░╚═╝░░░██║░░░██║░░██║██████╔╝░╚████╔╝░
                ░╚████╔╝░██║██║░░██╗░░░██║░░░██║░░██║██╔══██╗░░╚██╔╝░░
                ░░╚██╔╝░░██║╚█████╔╝░░░██║░░░╚█████╔╝██║░░██║░░░██║░░░
                ░░░╚═╝░░░╚═╝░╚════╝░░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝░░░╚═╝░░░
                """);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void get7LivesASCII() {
        System.out.println("""
                +---+
                    |
                    |
                    |
                    |
                    |
                =========
                """);
    }

    private static void get6LivesASCII() {
        System.out.println("""
                +---+
                |   |
                    |
                    |
                    |
                    |
                =========
                """);
    }

    private static void get5LivesASCII() {
        System.out.println("""
                +---+
                |   |
                O   |
                    |
                    |
                    |
                =========
                """);
    }

    private static void get4LivesASCII() {
        System.out.println("""
                +---+
                |   |
                O   |
                |   |
                    |
                    |
                =========
                """);
    }

    private static void get3LivesASCII() {
        System.out.println("""  
                 +---+
                 |   |
                 O   |
                /|   |
                     |
                     |
                 =========
                 """);
    }

    private static void get2LivesASCII() {
        System.out.println("""
                 +---+
                 |   |
                 O   |
                /|\\  |
                     |
                     |
                 =========
                 """);
    }

    private static void get1LivesASCII() {
        System.out.println("""
                 +---+
                 |   |
                 O   |
                /|\\  |
                /    |
                     |
                 =========
                 """);
    }

    private static void get0LivesASCII() {
        System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "GAME OVER" + ConsoleColors.RESET);
        System.out.println("""
                 +---+
                 |   |
                 O   |
                /|\\  |
                / \\  |
                     |
                 =========
                 """);
    }
}
