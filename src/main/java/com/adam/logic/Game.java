package com.adam.logic;

import com.adam.logic.words.Category;
import com.adam.logic.words.WordGenerator;
import com.adam.logic.words.WordGeneratorFileImpl;
import com.adam.player.Player;
import lombok.Getter;
import lombok.Setter;
import java.util.*;
import java.util.stream.Collectors;

import static com.adam.helpers.Helper.getRandomNumber;

@Getter
@Setter
public class Game {
    private DifficultyLevel difficulty;
    private String wordToGuess;
    private Category category;
    private List<Character> wordToGuessChars;
    private List<Character> usedLetters;
    private List<Character> revealedLetters;
    private WordGenerator wordGenerator;

    public Game(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
        this.wordGenerator = new WordGeneratorFileImpl();
    }

    public void gameSetup() {
        this.revealedLetters = new ArrayList<>();
        this.usedLetters = new ArrayList<>();
        this.category = getRandomCategory();
        this.wordToGuess = getRandomWord(category);
        this.wordToGuessChars = wordToGuess.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
    }

    public boolean isGameRunning(Player player) {
        return player.getRemainingLives() > 0 && wordToGuessChars.size() > 0;
    }

    public void correctLetterGuessedUpdate(char guessedLetter) {
        usedLetters.add(guessedLetter);
        revealedLetters.add(guessedLetter);
    }

    public void wrongLetterGuessedUpdate(char guessedLetter) {
        usedLetters.add(guessedLetter);
    }

    public boolean hasPlayerWon() {
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
