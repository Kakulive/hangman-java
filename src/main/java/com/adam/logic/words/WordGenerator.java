package com.adam.logic.words;

import java.util.List;

public interface WordGenerator {
    List<String> getWordsForCategory(Category category);
}
