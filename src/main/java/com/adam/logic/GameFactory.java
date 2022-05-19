package com.adam.logic;

import com.adam.logic.words.Category;
import com.adam.logic.words.WordGenerator;
import com.adam.logic.words.WordGeneratorFileImpl;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.adam.helpers.Helper.getRandomNumber;

public class GameFactory {
    private final WordGenerator wordGenerator;

    public GameFactory() {
        this.wordGenerator = new WordGeneratorFileImpl();
    }

    public GameFactory(WordGenerator wordGenerator) {
        this.wordGenerator = wordGenerator;
    }

    public Game getGame(int userDifficultySelection){
        final var difficultyLevel = setDifficulty(userDifficultySelection);
        final var game = new Game(difficultyLevel);
        gameSetup(game);
        return game;
    }

    private void gameSetup(Game game) {
        game.setRevealedLetters(new ArrayList<>());
        game.setUsedLetters(new ArrayList<>());
        final var category = getRandomCategory();
        game.setCategory(category);
        game.setWordToGuess(getRandomWord(category));
        final var wordToGuessChars = game.getWordToGuess().chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        game.setWordToGuessChars(wordToGuessChars);
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

    private Category getRandomCategory() {
        final var randomCategoryNumber = getRandomNumber(Category.values().length);

        return Category.values()[randomCategoryNumber];
    }

    private String getRandomWord(Category category) {
        final var wordsForCategory = wordGenerator.getWordsForCategory(category);
        final var randomWordNumber = getRandomNumber(wordsForCategory.size());

        return wordsForCategory.get(randomWordNumber).toUpperCase(Locale.ROOT);
    }
}
