package com.adam.player;

import com.adam.logic.DifficultyLevel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void whenPlayerIsCreatedWithSelectedDifficulty_ProperRemainingLivesAreSet() {
        //GIVEN
        var expectedEasyLives = 7;
        var expectedMediumLives = 5;
        var expectedHardLives = 3;
        var expectedImpossibleLives = 1;
        //WHEN
        var easyPlayer = new Player(DifficultyLevel.EASY);
        var mediumPlayer = new Player(DifficultyLevel.MEDIUM);
        var hardPlayer = new Player(DifficultyLevel.HARD);
        var impossiblePlayer = new Player(DifficultyLevel.IMPOSSIBLE);
        //THEN
        assertEquals(expectedEasyLives, easyPlayer.getRemainingLives());
        assertEquals(expectedMediumLives, mediumPlayer.getRemainingLives());
        assertEquals(expectedHardLives, hardPlayer.getRemainingLives());
        assertEquals(expectedImpossibleLives, impossiblePlayer.getRemainingLives());
    }
}