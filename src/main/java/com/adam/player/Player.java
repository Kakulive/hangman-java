package com.adam.player;

import lombok.Data;

@Data
public class Player {
    private String name;
    private int remainingLives;

    public Player() {
        this.remainingLives = setInitialLives();
    }

    private int setInitialLives() {
        return 7;
    }
}
