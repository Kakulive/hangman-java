package com.adam.logic;

import com.adam.input.UserInput;
import com.adam.logic.words.Category;
import com.adam.player.Player;
import com.adam.view.View;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import static com.adam.helpers.Helper.getRandomNumber;
import static com.adam.input.UserInput.getUserInput;
import static com.adam.view.View.*;

@Getter
@Setter
public class Game {
    private List<Character> usedLetters;
    private Player player;
    private Category category;
    private String wordToGuess;
    private List<Character> wordToGuessChars;
    private List<Character> revealedLetters;

    public Game() {
    }

    public void start() {
        this.revealedLetters = new ArrayList<>();
        this.usedLetters = new ArrayList<>();
        this.player = new Player();
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
            printWord(wordToGuess, revealedLetters);
            System.out.println("");
            printGameState(player.getRemainingLives());
            char guessedLetter = UserInput.getCharFromUser();
            if (usedLetters.contains(guessedLetter)) {
                printMessage("The letter has already been used, try another one!");
                continue;
            } else if (wordToGuessChars.contains(guessedLetter)) {
                usedLetters.add(guessedLetter);
                revealedLetters.add(guessedLetter);
                printMessage(guessedLetter + " - That's a correct letter!");
            } else {
                printMessage("Wrong letter, try again!");
                player.setRemainingLives(player.getRemainingLives() - 1);
                usedLetters.add(guessedLetter);
            }
            if (isPlayerDead()) {
                printGameState(player.getRemainingLives());
                printMessage("You have lost all your lives!");
                String userInput = getUserInput("Would you like to play again? [y/n]" ).toLowerCase(Locale.ROOT);
                if (userInput.equals("y") || userInput.equals("yes")){
                    View.clearScreen();
                    start();
                } else {
                    System.exit(0);
                }
            }
            if (hasPlayerWon()) {
                printVictoryScreen(wordToGuess);
                String userInput = getUserInput("Would you like to play again? ").toLowerCase(Locale.ROOT);
                if (userInput.equals("y") || userInput.equals("yes")){
                    start();
                } else {
                    System.exit(0);
                }
            }
        }
    }



    private boolean isPlayerDead() {
        return player.getRemainingLives() <= 0;
    }

    private boolean hasPlayerWon() {
        for (char c : wordToGuessChars) {
            if (c == ' ') {
                continue;
            }
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
        List<String> wordsForCategory = getWordsForCategory(category);
        int randomWordNumber = getRandomNumber(wordsForCategory.size());

        return wordsForCategory.get(randomWordNumber).toUpperCase(Locale.ROOT);
    }

    private List<String> getWordsForCategory(Category category) {
        List<String> records = new ArrayList<>();
        InputStream stream;
        if (category.equals(Category.CAPITALS)) {
            stream = getClass().getClassLoader().getResourceAsStream("capitals.csv");
        } else if (category.equals(Category.COUNTRIES)) {
            stream = getClass().getClassLoader().getResourceAsStream("countries.csv");
        } else {
            stream = getClass().getClassLoader().getResourceAsStream("animals.csv");
        }

        if (stream == null) {
            throw new IllegalArgumentException("File not found");
        }
        Scanner scanner = new Scanner(stream);
        while (scanner.hasNextLine()) {
            records.add((scanner.nextLine()));
        }
        return records;
    }

    private boolean isGameRunning() {
        return player.getRemainingLives() > 0 && wordToGuessChars.size() > 0;
    }
}
