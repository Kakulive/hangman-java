package com.adam.logic;

import com.adam.input.UserInteractionController;

public class GameFactory {
    private final UserInteractionController input;

    public GameFactory() {
        this.input = new UserInteractionController();
    }

    public Game getGame(){
        int userDifficultySelection = input.getDifficultyFromUser();
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
