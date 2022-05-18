package com.adam.player;

import com.adam.logic.DifficultyLevel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void whenPlayerIsCreatedWithSelectedDifficulty_ProperRemainingLivesAreSet() {
        //GIVEN
        int expectedEasyLives = 7;
        int expectedMediumLives = 5;
        int expectedHardLives = 3;
        int expectedImpossibleLives = 1;
        //WHEN
        Player easyPlayer = new Player(DifficultyLevel.EASY);
        Player mediumPlayer = new Player(DifficultyLevel.MEDIUM);
        Player hardPlayer = new Player(DifficultyLevel.HARD);
        Player impossiblePlayer = new Player(DifficultyLevel.IMPOSSIBLE);
        //THEN
        assertEquals(expectedEasyLives, easyPlayer.getRemainingLives());
        assertEquals(expectedMediumLives, mediumPlayer.getRemainingLives());
        assertEquals(expectedHardLives, hardPlayer.getRemainingLives());
        assertEquals(expectedImpossibleLives, impossiblePlayer.getRemainingLives());
    }
}