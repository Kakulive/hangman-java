package com.adam.view.impl;

import com.adam.helpers.Helper;
import com.adam.view.ConsoleColors;
import com.adam.view.MenuView;

import java.util.List;

public class ConsoleMenuView implements MenuView {
    private final Helper helper = new Helper();

    public void printWelcomeScreen() {
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Welcome to HANGMAN" + ConsoleColors.RESET);
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
}
