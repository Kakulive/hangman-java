package com.adam.input;

import com.adam.view.GameView;
import com.adam.view.impl.ConsoleGameView;
import com.adam.view.impl.ConsoleMenuView;
import com.adam.view.MenuView;

import java.util.*;

public class UserInteractionController {
    private static final List<Character> alphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    private static final Scanner scanner = new Scanner(System.in);

    MenuView menuView;
    GameView gameView;

    public UserInteractionController() {
        this.menuView = new ConsoleMenuView();
        this.gameView = new ConsoleGameView();
    }

    public String getUserInput(String message) {
        gameView.printMessage(message);
        return scanner.nextLine();
    }

    public Character getCharFromUser() {
        gameView.printMessage("Please guess the next letter: ");
        String givenLetter = "";

        while (!(isLetterValid(givenLetter))) {
            givenLetter = scanner.nextLine().toUpperCase(Locale.ROOT);
            if (givenLetter.equals("QUIT")){
                System.exit(0);
            }
            if (isLetterValid(givenLetter)) {
                return givenLetter.charAt(0);
            } else {
                gameView.printMessage("Please select a valid letter: ");
            }
        }
        throw new IllegalStateException("Error has occurred during letter selection from user");
    }

    private boolean isLetterValid(String givenLetter) {
        return givenLetter.length() == 1 && alphabet.contains(givenLetter.charAt(0));
    }

    public int getDifficultyFromUser() {
        menuView.difficultySelection();
        int userSelection;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number, try again!");
                scanner.next();
            }
            userSelection = scanner.nextInt();
            if (userSelection <= 0 || userSelection > 4) {
                gameView.printMessage("Please select valid difficulty level");
            }
        } while (userSelection <= 0 || userSelection > 4);
        System.out.println("Thank you! Got " + userSelection);
        scanner.nextLine();
        return userSelection;
    }
}
