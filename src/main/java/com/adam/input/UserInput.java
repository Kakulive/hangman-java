package com.adam.input;

import java.util.*;

import static com.adam.view.View.difficultySelection;
import static com.adam.view.View.printMessage;

public class UserInput {
    private static final List<Character> alphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    private static final Scanner scanner = new Scanner(System.in);

    public static String getUserInput(String message) {
        printMessage(message);
        return scanner.nextLine();
    }

    public static Character getCharFromUser() {
        printMessage("Please guess the next letter: ");
        String givenLetter = "";

        while (!(isLetterValid(givenLetter))) {
            givenLetter = scanner.nextLine().toUpperCase(Locale.ROOT);
            if (isLetterValid(givenLetter)) {
                return givenLetter.charAt(0);
            } else {
                printMessage("Please select a valid letter: ");
            }
        }
        throw new IllegalStateException("Error has occurred during letter selection from user");
    }

    private static boolean isLetterValid(String givenLetter) {
        return givenLetter.length() == 1 && alphabet.contains(givenLetter.charAt(0));
    }

    public static int getDifficultyFromUser() {
        difficultySelection();
        int userSelection;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number, try again!");
                scanner.next();
            }
            userSelection = scanner.nextInt();
            if (userSelection <= 0 || userSelection > 4) {
                printMessage("Please select valid difficulty level");
            }
        } while (userSelection <= 0 || userSelection > 4);
        System.out.println("Thank you! Got " + userSelection);
        return userSelection;
    }
}
