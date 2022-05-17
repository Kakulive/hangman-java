package com.adam.input;

import com.adam.view.View;

import java.util.Scanner;

public class UserInput {
    private static Scanner scanner = new Scanner(System.in);

    public static String getUserInput(String message){
        View.printMessage(message);

        return scanner.nextLine();
    }

    public static char getCharFromUser(){
        View.printMessage("Please guess the next letter: ");

        return scanner.next().charAt(0);
    }
}
