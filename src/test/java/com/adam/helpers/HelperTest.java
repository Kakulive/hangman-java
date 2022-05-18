package com.adam.helpers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    @Test
    void whenGeneratingRandomNumber_NumberIsGreaterThanZeroAndLowerThanBoundary() {
        //GIVEN
        int randomNumber;
        int boundary;
        //WHEN
        boundary = 10;
        randomNumber = Helper.getRandomNumber(boundary);
        //THEN
        assertTrue(randomNumber >= 0);
        assertTrue(randomNumber < boundary);
    }

    @Test
    void whenReadingFromResourcesFile_FileIsReadCorrectly() {
        //GIVEN
        Helper helper = new Helper();
        List<String> expectedAnimalsList = Arrays.asList("Cat", "Cattle", "Dog", "Donkey");
        //WHEN
        List<String> animalsList = helper.readFromFile("animals_test.csv");
        //THEN
        assertNotNull(animalsList);
        assertEquals(expectedAnimalsList, animalsList);
    }
}