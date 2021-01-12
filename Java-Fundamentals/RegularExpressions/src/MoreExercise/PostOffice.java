package MoreExercise;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostOffice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Character, Integer> capitalLetterAndLength = new LinkedHashMap<>();

        String[] parts = scanner.nextLine().split("\\|");

        String letters = firstPart(parts[0]);
        secondPart(letters, parts[1], capitalLetterAndLength);
        thirdPart(letters, parts[2], capitalLetterAndLength);
    }

    private static void thirdPart(String letters, String part, Map<Character, Integer> capitalLetterAndLength) {
        String[] words = part.split("\\s+");
        for (int i = 0; i < letters.length(); i++) {
            char firstLetter = letters.charAt(i);
            int length = capitalLetterAndLength.get(firstLetter);

            for (String word : words) {
                int lengthWord = word.length();
                char firstChar = word.charAt(0);

                if (lengthWord == length && firstLetter == firstChar) {
                    System.out.println(word);
                }
            }

        }
    }

    private static void secondPart(String letters, String part, Map<Character, Integer> capitalLetterAndLength) {
        Pattern pattern = Pattern.compile("(?<ascii>[0-9]{2}):(?<length>[0-9]{2})");
        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);
            Matcher matcher = pattern.matcher(part);
            while (matcher.find()) {
                char ascii = (char) Integer.parseInt(matcher.group("ascii"));
                int length = Integer.parseInt(matcher.group("length"));
                if (ascii == letter && !capitalLetterAndLength.containsKey(ascii)) {
                    capitalLetterAndLength.put(ascii, length + 1);
                }
            }
        }
    }

    private static String firstPart(String part) {
        String letters = "";
        Pattern pattern = Pattern.compile("([#$%*&])(?<word>[A-Z]+)\\1");
        Matcher matcher = pattern.matcher(part);
        while (matcher.find()) {
            String word = matcher.group("word");
            letters += word;
        }
        return letters;
    }
}