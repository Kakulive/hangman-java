package com.adam.logic;

import com.adam.input.UserInteractionController;
import com.adam.logic.words.Category;
import com.adam.view.GameView;
import com.adam.view.MenuView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
        List<Category> expectedCategories = Arrays.asList(Category.COUNTRIES, Category.CAPITALS, Category.ANIMALS);
        //WHEN
        GameController gameController = new GameController(menuView, gameView, input);
        Category category = gameController.getGame().getCategory();
        //THEN
        assertNotNull(category);
        assertTrue(expectedCategories.contains(category));
    }

    @Test
    void whenGameIsCreated_PlayerNameIsProperlyAssigned() {
        //GIVEN
        String expectedName = "Steve";
        //WHEN
        GameController gameController = new GameController(menuView, gameView, input);
        String playerName = gameController.getPlayer().getName();
        //THEN
        assertNotNull(playerName);
        assertEquals(expectedName, playerName);
    }
}