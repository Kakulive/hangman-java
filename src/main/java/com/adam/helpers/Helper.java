package com.adam.helpers;

import java.util.Random;

public class Helper {
    private static final Random random = new Random();

    public static int getRandomNumber(int upperBound){
        return random.nextInt(upperBound);
    }
}
