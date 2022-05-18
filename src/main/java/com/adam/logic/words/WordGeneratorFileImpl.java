package com.adam.logic.words;

import com.adam.helpers.Helper;

import java.util.List;

public class WordGeneratorFileImpl implements WordGenerator{
    private final Helper helper = new Helper();

    public List<String> getWordsForCategory(Category category) {
        switch (category){
            case CAPITALS -> {
                return helper.readFromFile("capitals.csv");
            }
            case COUNTRIES -> {
                return helper.readFromFile("countries.csv");
            }
            case ANIMALS -> {
                return helper.readFromFile("animals.csv");
            }
        }
        throw new IllegalArgumentException("Unknown category " + category + ". Cannot properly select word.");
    }
}
