package com.adam.logic;

import com.adam.logic.words.Category;
import com.adam.logic.words.WordGenerator;
import com.adam.logic.words.WordGeneratorFileImpl;

import java.util.ArrayList;
import java.util.List;
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
        DifficultyLevel difficultyLevel = setDifficulty(userDifficultySelection);
        Game game = new Game(difficultyLevel);
        gameSetup(game);
        return game;
    }

    private void gameSetup(Game game) {
        game.setRevealedLetters(new ArrayList<>());
        game.setUsedLetters(new ArrayList<>());
        Category category = getRandomCategory();
        game.setCategory(category);
        game.setWordToGuess(getRandomWord(category));
        List<Character> wordToGuessChars = game.getWordToGuess().chars().mapToObj(c -> (char) c).collect(Collectors.toList());
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
        int randomCategoryNumber = getRandomNumber(Category.values().length);

        return Category.values()[randomCategoryNumber];
    }

    private String getRandomWord(Category category) {
        List<String> wordsForCategory = wordGenerator.getWordsForCategory(category);
        int randomWordNumber = getRandomNumber(wordsForCategory.size());

        return wordsForCategory.get(randomWordNumber).toUpperCase(Locale.ROOT);
    }
}
