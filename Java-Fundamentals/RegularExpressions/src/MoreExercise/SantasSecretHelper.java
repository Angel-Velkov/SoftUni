package MoreExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SantasSecretHelper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> goodKids = new ArrayList<>();
        String regex = "@(?<name>[A-za-z]+)[^@\\-!:>]*?!(?<type>[GN])!";
        Pattern pattern = Pattern.compile(regex);

        int n = Integer.parseInt(scanner.nextLine());
        String line;
        while (!"end".equals(line = scanner.nextLine())) {
            StringBuilder message = new StringBuilder();
            message.append(line);
            encryptingMessage(message, n);
            Matcher matcher = pattern.matcher(message);
            if (matcher.find()) {
                if (matcher.group("type").equals("G")) {
                    goodKids.add(matcher.group("name"));
                }
            }
        }

        goodKids.forEach(System.out::println);
    }

    private static void encryptingMessage(StringBuilder line, int n) {
        for (int i = 0; i < line.length(); i++) {
            char newSymbol = (char) (line.charAt(i) - n);
            line.setCharAt(i, newSymbol);
        }
    }
}
