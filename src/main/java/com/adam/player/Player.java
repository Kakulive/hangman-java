package com.adam.player;

import com.adam.logic.DifficultyLevel;
import lombok.Getter;

@Getter
public class Player {
    private String name;
    private int remainingLives;

    public Player(DifficultyLevel difficulty) {
        this.remainingLives = setInitialLives(difficulty);
    }

    public void hitPlayer() {
        this.remainingLives = this.remainingLives - 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int setInitialLives(DifficultyLevel difficulty) {
        switch (difficulty) {
            case MEDIUM -> {
                return 5;
            }
            case HARD -> {
                return 3;
            }
            case IMPOSSIBLE -> {
                return 1;
            }
            default -> {
                return 7;
            }
        }
    }
}
