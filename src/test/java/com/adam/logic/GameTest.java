package com.adam.logic;

import com.adam.input.UserInteractionController;
import com.adam.logic.words.Category;
import com.adam.logic.words.WordGenerator;
import com.adam.logic.words.WordGeneratorFileImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameTest {

    @Mock
    UserInteractionController input;

    @BeforeEach
    void mockGameSetup() {
        when(input.getUserInput("Name: ")).thenReturn("Steve");
        when(input.getDifficultyFromUser()).thenReturn(1);
    }

//    @Test
//    void whenGameStarts_NameIsCorrectlyAssigned() {
//        //GIVEN
////        Game game = new Game();
//        String expectedPlayerName = "Steve";
//        //WHEN
////        game.start();
//        //THEN
////        assertEquals(expectedPlayerName, game.getPlayer().getName());
//    }
//
//    @Test
//    void whenGameStarts_RevealedAndUsedLettersAreEmpty() {
//        //GIVEN
////        Game game = new Game();
//        //WHEN
////        game.start();
//        //THEN
////        assertEquals(Collections.emptyList(), game.getRevealedLetters());
////        assertEquals(Collections.emptyList(), game.getUsedLetters());
//    }
//
//    @Test
//    void whenGameStarts_CorrectCategoryIsSelected() {
//        //GIVEN
////        Game game = new Game();
//        List<Category> availableCategories = Arrays.asList(Category.CAPITALS, Category.COUNTRIES, Category.ANIMALS);
//        //WHEN
////        game.start();
//        //THEN
//        assertTrue(availableCategories.contains(game.getCategory()));
//    }

//    @Test
//    void whenCategoryIsSelected_WordIsChosenFromCorrectList() {
//        //GIVEN
//        Game game = new Game();
//        String expectedAnimalWord = "Horse";
//        String expectedCountryWord = "Austria";
//        String expectedCapitalWord = "Brussels";
//        //WHEN
////        game.start();
//        WordGenerator wordGenerator = new WordGeneratorFileImpl();
//        List<String> wordsForCategory = wordGenerator.getWordsForCategory(game.getCategory());
//        //THEN
//        if (game.getCategory().equals(Category.ANIMALS)) {
//            assertTrue(wordsForCategory.contains(expectedAnimalWord));
//        } else if (game.getCategory().equals(Category.COUNTRIES)) {
//            assertTrue(wordsForCategory.contains(expectedCountryWord));
//        } else if (game.getCategory().equals(Category.CAPITALS)) {
//            assertTrue(wordsForCategory.contains(expectedCapitalWord));
//        }
//    }


}