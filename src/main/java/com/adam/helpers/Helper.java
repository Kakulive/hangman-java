package com.adam.helpers;

import java.util.Random;

public class Helper {
    private static Random random = new Random();

    public static int getRandomNumber(int upperBound){
        return random.nextInt(upperBound);
    }
}