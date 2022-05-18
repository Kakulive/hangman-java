package com.adam.logic.words;

import com.adam.helpers.Helper;

import java.util.List;

public class WordGeneratorFileImpl implements WordGenerator{
    private final Helper helper = new Helper();

    public List<String> getWordsForCategory(Category category) {
        List<String> records;
        if (category.equals(Category.CAPITALS)) {
            records = helper.readFromFile("capitals.csv");
        } else if (category.equals(Category.COUNTRIES)) {
            records = helper.readFromFile("countries.csv");
        } else {
            records = helper.readFromFile("animals.csv");
        }
        return records;
    }

}
