package com.adam.input;

import com.adam.view.View;

import java.util.*;

public class UserInput {
    private static List<Character> alphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    private static Scanner scanner = new Scanner(System.in);

    public static String getUserInput(String message) {
        View.printMessage(message);
        return scanner.nextLine();
    }

    public static Character getCharFromUser() {
        View.printMessage("Please guess the next letter: ");
        String givenLetter = "";

        while (!(isLetterValid(givenLetter))) {
            givenLetter = scanner.nextLine().toUpperCase(Locale.ROOT);
            if (isLetterValid(givenLetter)) {
                return givenLetter.charAt(0);
            } else {
                View.printMessage("Please select a valid letter: ");
            }
        }
        throw new IllegalStateException("Error has occurred during letter selection from user");
    }

    public static int getDifficultyFromUser() {
        View.difficultySelection();
        int userSelection = scanner.nextInt();
        scanner.nextLine();
        return userSelection;
    }

    private static boolean isLetterValid(String givenLetter) {
        return givenLetter.length() == 1 && alphabet.contains(givenLetter.charAt(0));
    }
}
