package com.adam.view;

import java.util.List;

public class View {
    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printWord(String word, List<Character> revealedLetters) {
        StringBuilder sb = new StringBuilder();
        sb.append("Word to guess: ");
        char[] wordChars = word.toCharArray();
        for (char c : wordChars) {
            if (revealedLetters.contains(c)) {
                sb.append(c).append("  ");
            } else if (c == ' ') {
                sb.append(" ");
            } else {
                sb.append("_ ");
            }
        }
        System.out.println(sb);
    }

    public static void printLivesLeft(int remainingLives) {
        System.out.println("LIVES: " + remainingLives);
    }

    public static void difficultySelection() {
        System.out.println("Please select the difficulty:\n" +
                           "1 - Easy (7 lives)\n" +
                           "2 - Medium (5 lives)\n" +
                           "3 - Hard (3 lives)\n" +
                           "4 - Impossible (1 life)\n");
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

    public static void printVictoryScreen(String word) {
        System.out.println("Congratulations! You have won!\nYour word is " + word + "\n");
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
                =========""");
    }

    private static void get6LivesASCII() {
        System.out.println("""
                +---+
                |   |
                    |
                    |
                    |
                    |
                =========""");
    }

    private static void get5LivesASCII() {
        System.out.println("""
                +---+
                |   |
                O   |
                    |
                    |
                    |
                =========""");
    }

    private static void get4LivesASCII() {
        System.out.println("""
                +---+
                |   |
                O   |
                |   |
                    |
                    |
                =========""");
    }

    private static void get3LivesASCII() {
        System.out.println("""  
                 +---+
                 |   |
                 O   |
                /|   |
                     |
                     |
                 =========""");
    }

    private static void get2LivesASCII() {
        System.out.println("""
                 +---+
                 |   |
                 O   |
                /|\\  |
                     |
                     |
                 =========""");
    }

    private static void get1LivesASCII() {
        System.out.println("""
                 +---+
                 |   |
                 O   |
                /|\\  |
                /    |
                     |
                 =========""");
    }

    private static void get0LivesASCII() {
        System.out.println("""
                 GAME OVER
                 +---+
                 |   |
                 O   |
                /|\\  |
                / \\  |
                     |
                 =========""");
    }
}
