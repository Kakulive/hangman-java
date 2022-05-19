package com.adam.logic;

import com.adam.input.UserInteractionController;
import com.adam.logic.words.Category;
import com.adam.view.GameView;
import com.adam.view.MenuView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameControllerTest {

    UserInteractionController input = mock(UserInteractionController.class);
    MenuView menuView = mock(MenuView.class);
    GameView gameView = mock(GameView.class);

    @BeforeEach
    void setupTests() {
        when(input.getDifficultyFromUser()).thenReturn(1);
        when(input.getUserInput(anyString())).thenReturn("Steve");
    }

    @Test
    void whenGameIsCreated_CategoryIsProperlySelected() {
        //GIVEN
        var expectedCategories = Arrays.asList(Category.COUNTRIES, Category.CAPITALS, Category.ANIMALS);
        //WHEN
        var gameController = new GameController(menuView, gameView, input);
        var category = gameController.getGame().getCategory();
        //THEN
        assertNotNull(category);
        assertTrue(expectedCategories.contains(category));
    }

    @Test
    void whenGameIsCreated_PlayerNameIsProperlyAssigned() {
        //GIVEN
        var expectedName = "Steve";
        //WHEN
        var gameController = new GameController(menuView, gameView, input);
        var playerName = gameController.getPlayer().getName();
        //THEN
        assertNotNull(playerName);
        assertEquals(expectedName, playerName);
    }
}