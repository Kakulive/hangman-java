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
        GameController gameController = new GameController(menuView, gameView, input);
        List<Category> expectedCategories = Arrays.asList(Category.COUNTRIES, Category.CAPITALS, Category.ANIMALS);
        //WHEN
        //THEN
        assertTrue(expectedCategories.contains(gameController.getGame().getCategory()));
    }

    @Test
    void whenGameIsCreated_PlayerNameIsProperlyAssigned() {
        //GIVEN
        GameController gameController = new GameController(menuView, gameView, input);
        String expectedName = "Steve";
        //WHEN
        //THEN
        assertEquals(expectedName, gameController.getPlayer().getName());
    }
}