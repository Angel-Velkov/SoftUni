package MoreExercise;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostOffice {
    public static void main(String[] args) {
        Map<Character, Integer> capitalLettersAndTheirLength = new LinkedHashMap<>();
        Pattern firstPattern = Pattern.compile("([#$%*&])(?<word>[A-Z]+)\\1");
        Pattern secondPattern = Pattern.compile("(?<number>[0-9]{2}):(?<length>[0-9]{2})");
        Pattern thirdPattern = Pattern.compile("[A-Z].*");

        String[] input = new Scanner(System.in).nextLine().split("\\|");

        Matcher firstMatcher = firstPattern.matcher(input[0]);
        while (firstMatcher.find()) {
            collectCapitalLetters(capitalLettersAndTheirLength, firstMatcher.group("word"));
        }

        Matcher secondMatcher = secondPattern.matcher(input[1]);
        while (secondMatcher.find()) {
            int asciCode = Integer.parseInt(secondMatcher.group("number"));
            int length = Integer.parseInt(secondMatcher.group("length"));
            if (0 < length && length <= 20) {
                recordTheLengthOFTheWord(capitalLettersAndTheirLength, asciCode, length);
            }
        }

        String[] words = input[2].split("\\s+");
        for (Map.Entry<Character, Integer> entry : capitalLettersAndTheirLength.entrySet()) {
            for (String word : words) {
                Matcher thirdMatcher = thirdPattern.matcher(word);
                if (thirdMatcher.find()) {
                    if (entry.getKey() == thirdMatcher.group().charAt(0)
                            && entry.getValue() == thirdMatcher.group().length()) {
                        System.out.println(thirdMatcher.group());
                    }
                }
            }
        }
    }
    // TODO: методът не е в употреба
    private static Boolean chekIfTheWordIsCorrect(Map<Character, Integer> capitalLettersAndTheirLength, String word) {
        char firstLetter = word.charAt(0);
        if (capitalLettersAndTheirLength.containsKey(firstLetter)) {
            if (word.length() == capitalLettersAndTheirLength.get(firstLetter)) {
                return true;
            }
        }
        return false;
    }

    private static void recordTheLengthOFTheWord(Map<Character, Integer> capitalLettersAndTheirLength, int asciCode, int length) {
        char letter = (char) asciCode;
        if (capitalLettersAndTheirLength.containsKey(letter)) {
            if (capitalLettersAndTheirLength.get(letter) == 0) {
                capitalLettersAndTheirLength.put(letter, length + 1);
            }
        }
    }

    private static void collectCapitalLetters(Map<Character, Integer> capitalLetters, String word) {
        for (int i = 0; i < word.length(); i++) {
            char currentLetter = word.charAt(i);
            capitalLetters.put(currentLetter, 0);
        }
    }
}
