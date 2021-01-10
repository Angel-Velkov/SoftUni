package Exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

public class StarEnigma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> attacked = new ArrayList<>();
        List<String> destroyed = new ArrayList<>();

        Pattern countPattern = Pattern.compile("[star]", CASE_INSENSITIVE);

        String decryptRegex = "@(?<planetName>[A-Za-z]+)[^@\\-!;>]*?:(?<population>\\d+)[^@\\-!;>]*?!(?<attackType>A|D)![^@\\-!;>]*?->(?<soldiers>\\d+)";
        Pattern decryptPattern = Pattern.compile(decryptRegex);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String encrypt = scanner.nextLine();

            int counter = 0;
            Matcher matcher = countPattern.matcher(encrypt);

            while (matcher.find()) {
                counter++;
            }

            StringBuilder decrypt = new StringBuilder();
            for (int j = 0; j < encrypt.length(); j++) {
                char decryptedChar = (char) (encrypt.charAt(j) - counter);
                decrypt.append(decryptedChar);
            }

            matcher = decryptPattern.matcher(decrypt);
            if (matcher.find()) {
                String planetName = matcher.group("planetName");
                String attackType = matcher.group("attackType");

                if (attackType.equals("A")) {
                    attacked.add(planetName);
                } else {
                    destroyed.add(planetName);
                }
            }
        }

        System.out.println("Attacked planets: " + attacked.size());
        Collections.sort(attacked);
        attacked.forEach(p -> System.out.println("-> " + p));

        System.out.println("Destroyed planets: " + destroyed.size());
        Collections.sort(destroyed);
        destroyed.forEach(p -> System.out.println("-> " + p));
    }
}
