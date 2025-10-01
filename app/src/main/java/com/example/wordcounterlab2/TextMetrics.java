package com.example.wordcounterlab2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextMetrics {

    // Uses regex: counts occurrences of sentence terminators (., !, ?)
    public static int countSentences(String text) {
        if (text == null || text.isEmpty()) return 0;
        Pattern pattern = Pattern.compile("[.!?]+");
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) count++;
        return count;
    }

    // WITHOUT regex: counts words separated by spaces, commas, or dots
    public static int countWords(String text) {
        if (text == null) return 0;
        int length = text.length();
        boolean inWord = false;
        int count = 0;
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if (c != ' ' && c != ',' && c != '.') {
                if (!inWord) {
                    inWord = true;
                    count++;
                }
            } else {
                inWord = false;
            }
        }
        return count;
    }

    // WITHOUT regex: returns number of characters (including spaces)
    public static int countChars(String text) {
        if (text == null) return 0;
        return text.length();
    }

    // Uses regex: counts digit sequences like 123 or 42
    public static int countNumbers(String text) {
        if (text == null || text.isEmpty()) return 0;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) count++;
        return count;
    }
}
