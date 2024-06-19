package com.example.roughwork.Extras;

import java.util.*;
import java.util.logging.Logger;
import java.io.IOException;

public class FirstCharCapital {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please, enter any text: ");
        String userInput = sc.nextLine();


        System.out.println(firstCharToTitleCase(userInput));
    }

    public static String firstCharToTitleCase(String string) {

        if (string == null || string.isEmpty()) {
            return string;
        }

        String[] words = string.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {

                String formattedWord = capitalizeFirstAlphabet(word);
                result.append(formattedWord).append(" ");
            }
        }

        return result.toString().trim();
    }
    public static String capitalizeFirstAlphabet(String str) {
        if (str != null && !str.isEmpty()) {
            for (int i = 0; i < str.length(); i++) {
                char currentChar = str.charAt(i);
                if (Character.isLetter(currentChar)) {
                    return str.substring(0, i) + Character.toUpperCase(currentChar) + str.substring(i + 1);
                }
            }
        }
        return str;
    }
}
