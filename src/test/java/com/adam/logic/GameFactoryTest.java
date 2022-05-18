package com.adam.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameFactoryTest {

    @Test
    void whenCreatingNewGame_ProperDifficultyLevelIsAssigned() {
        //GIVEN
        int easyDifficulty = 1;
        int mediumDifficulty = 2;
        int hardDifficulty = 3;
        int impossibleDifficulty = 4;
        int randomDifficulty = 5;
        GameFactory gameFactory = new GameFactory();
        //WHEN
        Game easyGame = gameFactory.getGame(easyDifficulty);
        Game mediumGame = gameFactory.getGame(mediumDifficulty);
        Game hardGame = gameFactory.getGame(hardDifficulty);
        Game impossibleGame = gameFactory.getGame(impossibleDifficulty);
        Game randomGame = gameFactory.getGame(randomDifficulty);
        //THEN
        assertEquals(DifficultyLevel.EASY, easyGame.getDifficulty());
        assertEquals(DifficultyLevel.MEDIUM, mediumGame.getDifficulty());
        assertEquals(DifficultyLevel.HARD, hardGame.getDifficulty());
        assertEquals(DifficultyLevel.IMPOSSIBLE, impossibleGame.getDifficulty());
        assertEquals(DifficultyLevel.EASY, randomGame.getDifficulty());
    }
}