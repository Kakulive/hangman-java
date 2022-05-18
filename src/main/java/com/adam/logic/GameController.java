package com.adam.logic;

import com.adam.input.UserInteractionController;
import com.adam.player.Player;
import com.adam.view.GameView;
import com.adam.view.MenuView;
import com.adam.view.impl.ConsoleGameView;
import com.adam.view.impl.ConsoleMenuView;

import java.util.Locale;

public class GameController {
    private final GameFactory gameFactory = new GameFactory();
    private Player player;
    private Game game;
    private MenuView menuView;
    private GameView gameView;
    private UserInteractionController input;

    public GameController() {
        this.game = gameFactory.getGame();
        this.gameView = new ConsoleGameView();
        this.menuView = new ConsoleMenuView();
        this.input = new UserInteractionController();
    }

    public void startGame() {
        menuView.printWelcomeScreen();
        game.gameSetup();
        playerSetup();
        gameView.printCategory(game.getCategory());
        System.out.println(game.getWordToGuess()); //TODO remove cheat when not needed
        runGame();
    }

    private void runGame() {
        while (game.isGameRunning(player)) {
            displayUi();
            char guessedLetter = input.getCharFromUser();

            if (game.getUsedLetters().contains(guessedLetter)) {
                gameView.printMessage("The letter has already been used, try another one!");
                continue;
            } else if (game.getWordToGuessChars().contains(guessedLetter)) {
                correctGuessAction(guessedLetter);
            } else {
                wrongGuessAction(guessedLetter);
            }
            checkForLoss();
            checkForWin();
        }
    }

    private void correctGuessAction(char guessedLetter) {
        game.correctLetterGuessedUpdate(guessedLetter);
        gameView.printMessage(guessedLetter + " - That's a correct letter!");
    }

    private void wrongGuessAction(char guessedLetter) {
        player.hitPlayer();
        game.wrongLetterGuessedUpdate(guessedLetter);
        gameView.wrongLetterMessage();
    }

    private void playerSetup() {
        this.player = new Player(game.getDifficulty());
        setPlayerName();
        gameView.printMessage("\nWelcome " + player.getName());
    }

    private void setPlayerName() {
        menuView.nameSelection();
        String playerName = input.getUserInput("Name: ");
        player.setName(playerName);
    }

    private void checkForLoss() {
        if (player.isPlayerDead()) {
            gameView.printGameState(player.getRemainingLives());
            playAgainCheck();
        }
    }

    private void checkForWin() {
        if (game.hasPlayerWon()) {
            menuView.printVictoryScreen(game.getWordToGuess());
            playAgainCheck();
        }
    }

    private void displayUi() {
        gameView.printLivesLeft(player.getRemainingLives());
        gameView.printGuessedWord(game.getWordToGuess(), game.getRevealedLetters());
        menuView.printEmptyLine();
        gameView.printGameState(player.getRemainingLives());
    }

    private void playAgainCheck() {
        String userInput = input.getUserInput("\nWould you like to play again? [y/n]").toLowerCase(Locale.ROOT);
        if (userInput.equals("y") || userInput.equals("yes")) {
            menuView.clearScreen();
            startGame();
        } else {
            exitGame();
        }
    }

    private void exitGame() {
        System.exit(0);
    }
}
