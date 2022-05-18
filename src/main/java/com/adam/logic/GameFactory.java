package com.adam.logic;

public class GameFactory {

    public GameFactory() {
    }

    public Game getGame(int userDifficultySelection){
        DifficultyLevel difficultyLevel = setDifficulty(userDifficultySelection);
        return new Game(difficultyLevel);
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
}
