package com.adam.logic;

import com.adam.helpers.Helper;
import com.adam.input.UserInteractionController;
import com.adam.logic.words.Category;
import com.adam.logic.words.WordGenerator;
import com.adam.logic.words.WordGeneratorFileImpl;
import com.adam.player.Player;
import com.adam.view.GameView;
import com.adam.view.impl.ConsoleGameView;
import com.adam.view.impl.ConsoleMenuView;
import com.adam.view.MenuView;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

import static com.adam.helpers.Helper.getRandomNumber;

@Getter
@Setter
public class Game {
    private String wordToGuess;
    private Player player;
    private Category category;
    private List<Character> wordToGuessChars;
    private List<Character> usedLetters;
    private List<Character> revealedLetters;

    private MenuView menuView;
    private GameView gameView;
    private UserInteractionController input;
    private Helper helper;
    private WordGenerator wordGenerator;

    public Game() {
        this.gameView = new ConsoleGameView();
        this.menuView = new ConsoleMenuView();
        this.input = new UserInteractionController();
        this.helper = new Helper();
        this.wordGenerator = new WordGeneratorFileImpl();
    }

    public void start() {
        gameSetup();
        playerSetup();
        gameView.printCategory(category);
        System.out.println(wordToGuess); //TODO remove cheat when not needed
        run();
    }

    private void gameSetup() {
        menuView.printWelcomeScreen();
        this.revealedLetters = new ArrayList<>();
        this.usedLetters = new ArrayList<>();
        this.category = getRandomCategory();
        this.wordToGuess = getRandomWord(category);
        this.wordToGuessChars = wordToGuess.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
    }

    private void playerSetup() {
        int userDifficultySelection = input.getDifficultyFromUser();
        this.player = new Player(setDifficulty(userDifficultySelection));
        setPlayerName();
        gameView.printMessage("\nWelcome " + player.getName());
    }

    private void run() {
        while (isGameRunning()) {
            displayUi();
            char guessedLetter = input.getCharFromUser();

            if (usedLetters.contains(guessedLetter)) {
                gameView.printMessage("The letter has already been used, try another one!");
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
        gameView.printLivesLeft(player.getRemainingLives());
        gameView.printGuessedWord(wordToGuess, revealedLetters);
        menuView.printEmptyLine();
        gameView.printGameState(player.getRemainingLives());
    }

    private void correctLetterGuessed(char guessedLetter) {
        usedLetters.add(guessedLetter);
        revealedLetters.add(guessedLetter);
        gameView.printMessage(guessedLetter + " - That's a correct letter!");
    }

    private void wrongLetterGuessed(char guessedLetter) {
        player.hitPlayer();
        usedLetters.add(guessedLetter);
        gameView.wrongLetterMessage();
    }

    private void checkForLoss() {
        if (isPlayerDead()) {
            gameView.printGameState(player.getRemainingLives());
            playAgainCheck();
        }
    }

    private boolean isPlayerDead() {
        return player.getRemainingLives() <= 0;
    }

    private void checkForWin() {
        if (hasPlayerWon()) {
            menuView.printVictoryScreen(wordToGuess);
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
        String userInput = input.getUserInput("\nWould you like to play again? [y/n]").toLowerCase(Locale.ROOT);
        if (userInput.equals("y") || userInput.equals("yes")) {
            menuView.clearScreen();
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
        menuView.nameSelection();
        String playerName = input.getUserInput("Name: ");
        player.setName(playerName);
    }

    private Category getRandomCategory() {
        int randomCategoryNumber = getRandomNumber(Category.values().length);
        return Category.values()[randomCategoryNumber];
    }

    private String getRandomWord(Category category) {
        List<String> wordsForCategory = wordGenerator.getWordsForCategory(category);
        int randomWordNumber = getRandomNumber(wordsForCategory.size());

        return wordsForCategory.get(randomWordNumber).toUpperCase(Locale.ROOT);
    }
}
