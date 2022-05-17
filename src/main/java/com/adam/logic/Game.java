package com.adam.logic;

import com.adam.input.UserInput;
import com.adam.logic.words.CapitalsWords;
import com.adam.logic.words.Category;
import com.adam.logic.words.CountriesWords;
import com.adam.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.adam.helpers.Helper.getRandomNumber;
import static com.adam.input.UserInput.getUserInput;
import static com.adam.view.View.*;

@Getter
@Setter
public class Game {
    private final List<Character> usedLetters;
    private Player player;
    private Category category;
    private String wordToGuess;
    private List<Character> wordToGuessChars;
    private List<Character> revealedLetters;

    public Game() {
        this.usedLetters = new ArrayList<>();
        this.revealedLetters = new ArrayList<>();
        this.player = new Player();
    }

    public void start() {
        printMessage("Welcome to Hangman!");
        setPlayerName();
        printMessage("Welcome " + player.getName());
        this.category = getRandomCategory();
        printMessage("Category of your word is: " + category);
        this.wordToGuess = getRandomWord(category);
        this.wordToGuessChars = wordToGuess.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        System.out.println(wordToGuessChars); //TODO remove cheat
        run();
    }

    private void run() {
        while (isGameRunning()) {
            printGameState(player.getRemainingLives());
            char guessedLetter = UserInput.getCharFromUser();
            if (usedLetters.contains(guessedLetter)) {
                printMessage("The letter has already been used, try another one!");
                continue;
            } else if (wordToGuessChars.contains(guessedLetter)) {
                usedLetters.add(guessedLetter);
                revealedLetters.add(guessedLetter);
                printMessage(guessedLetter + " - That's a correct letter!");
                printWord(wordToGuess, revealedLetters);
            } else {
                printMessage("Wrong letter, try again!");
                player.setRemainingLives(player.getRemainingLives() - 1);
            }
            if (isPlayerDead()) {
                printGameState(player.getRemainingLives());
                printMessage("You have lost all your lives!");
                printMessage("Would you like to play again? ");
            }
            if (hasPlayerWon()) {
                printMessage("Congratulations! Your word was: " + wordToGuess);
                printMessage("Would you like to play again? ");
            }
        }
    }

    private boolean isPlayerDead() {
        return player.getRemainingLives() <= 0;
    }

    private boolean hasPlayerWon() {
        for (char c : wordToGuessChars) {
            if (!revealedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private void setPlayerName() {
        String playerName = getUserInput("Please tell me your name: ");
        player.setName(playerName);
    }

    private Category getRandomCategory() {
        int randomCategoryNumber = getRandomNumber(Category.values().length);
        return Category.values()[randomCategoryNumber];
    }

    private String getRandomWord(Category category) {
        if (category.equals(Category.CAPITALS)) {
            int randomWordNumber = getRandomNumber(CapitalsWords.values().length);
            return CapitalsWords.values()[randomWordNumber].toString();
        } else if (category.equals(Category.COUNTRIES)) {
            int randomWordNumber = getRandomNumber(CountriesWords.values().length);
            return CountriesWords.values()[randomWordNumber].toString();
        } else {
            throw new IllegalArgumentException("Unknown category " + category);
        }
    }

    private boolean isGameRunning() {
        return player.getRemainingLives() > 0 && wordToGuessChars.size() > 0;
    }


}
