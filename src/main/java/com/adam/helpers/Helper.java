package com.adam.helpers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Helper {
    private static final Random random = new Random();

    public static int getRandomNumber(int upperBound){
        return random.nextInt(upperBound);
    }

    public List<String> readFromFile(String filename){
        List<String> records = new ArrayList<>();
        InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);
        if (stream == null) {
            throw new IllegalArgumentException("File not found");
        }
        Scanner scanner = new Scanner(stream);
        while (scanner.hasNextLine()) {
            records.add((scanner.nextLine()));
        }
        return records;
    }
}
