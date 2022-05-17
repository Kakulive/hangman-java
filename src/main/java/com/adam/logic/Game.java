package com.adam.logic;

import com.adam.input.UserInput;
import com.adam.logic.words.Category;
import com.adam.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import static com.adam.helpers.Helper.getRandomNumber;
import static com.adam.input.UserInput.getDifficultyFromUser;
import static com.adam.input.UserInput.getUserInput;
import static com.adam.view.View.*;

@Getter
@Setter
public class Game {
    private String wordToGuess;
    private Player player;
    private Category category;
    private List<Character> wordToGuessChars;
    private List<Character> usedLetters;
    private List<Character> revealedLetters;

    public Game() {
    }

    public void start() {
        gameSetup();
        playerSetup();
        printCategory(category);
        System.out.println(wordToGuessChars); //TODO remove cheat
        run();
    }

    private void gameSetup() {
        printWelcomeScreen();
        this.revealedLetters = new ArrayList<>();
        this.usedLetters = new ArrayList<>();
        this.category = getRandomCategory();
        this.wordToGuess = getRandomWord(category);
        this.wordToGuessChars = wordToGuess.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
    }

    private void playerSetup() {
        int userDifficultySelection = getDifficultyFromUser();
        this.player = new Player(setDifficulty(userDifficultySelection));
        setPlayerName();
        printMessage("\nWelcome " + player.getName());
    }

    private void run() {
        while (isGameRunning()) {
            displayUi();
            char guessedLetter = UserInput.getCharFromUser();

            if (usedLetters.contains(guessedLetter)) {
                printMessage("The letter has already been used, try another one!");
                continue;
            } else if (wordToGuessChars.contains(guessedLetter)) {
                correctLetterGuessed(guessedLetter);
            } else {
                wrongLetterGuessed(guessedLetter);
            }
            checkForLoss();
            checkForWin();
        }
    }

    private boolean isGameRunning() {
        return player.getRemainingLives() > 0 && wordToGuessChars.size() > 0;
    }

    private void displayUi() {
        printLivesLeft(player.getRemainingLives());
        printGuessedWord(wordToGuess, revealedLetters);
        printEmptyLine();
        printGameState(player.getRemainingLives());
    }

    private void correctLetterGuessed(char guessedLetter) {
        usedLetters.add(guessedLetter);
        revealedLetters.add(guessedLetter);
        printMessage(guessedLetter + " - That's a correct letter!");
    }

    private void wrongLetterGuessed(char guessedLetter) {
        player.setRemainingLives(player.getRemainingLives() - 1);
        usedLetters.add(guessedLetter);
        wrongLetterMessage();
    }

    private void checkForLoss() {
        if (isPlayerDead()) {
            printGameState(player.getRemainingLives());
            playAgainCheck();
        }
    }

    private boolean isPlayerDead() {
        return player.getRemainingLives() <= 0;
    }

    private void checkForWin() {
        if (hasPlayerWon()) {
            printVictoryScreen(wordToGuess);
            playAgainCheck();
        }
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

    private void playAgainCheck() {
        String userInput = getUserInput("\nWould you like to play again? [y/n]").toLowerCase(Locale.ROOT);
        if (userInput.equals("y") || userInput.equals("yes")) {
            clearScreen();
            start();
        } else {
            exitGame();
        }
    }

    private void exitGame() {
        System.exit(0);
    }

    private DifficultyLevel setDifficulty(int userSelection) {
        switch (userSelection) {
            case 2 -> {
                return DifficultyLevel.MEDIUM;
            }
            case 3 -> {
                return DifficultyLevel.HARD;
            }
            case 4 -> {
                return DifficultyLevel.IMPOSSIBLE;
            }
            default -> {
                return DifficultyLevel.EASY;
            }
        }
    }

    private void setPlayerName() {
        nameSelection();
        String playerName = getUserInput("Name: ");
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
}
