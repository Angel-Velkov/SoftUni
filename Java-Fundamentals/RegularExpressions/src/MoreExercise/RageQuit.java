package MoreExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RageQuit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String regex = "(?<word>[\\D]+)(?<count>\\d+)";
        Pattern pattern = Pattern.compile(regex);

        String line = scanner.nextLine();
        StringBuilder message = new StringBuilder();
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            int count = Integer.parseInt(matcher.group("count"));
            String word = matcher.group("word");
            repeatsWordAndMakesItInUpperCase(message, word, count);
        }
        System.out.println("Unique symbols used: " + numberOfUniqueSymbols(message));
        System.out.println(message);
    }

    private static int numberOfUniqueSymbols(StringBuilder message) {
        List<Character> symbols = new ArrayList<>();
        for (int i = 0; i < message.length(); i++) {
            char currentSymbol = message.charAt(i);
            if (!symbols.contains(currentSymbol)) {
                symbols.add(currentSymbol);
            }
        }
        return symbols.size();
    }

    private static void repeatsWordAndMakesItInUpperCase(StringBuilder text, String word, int count) {
        for (int i = 0; i < count; i++) {
            text.append(word.toUpperCase());
        }
    }
}
