package Lab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class OddOccurrences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> count = new LinkedHashMap<>();

        String[] words = scanner.nextLine().split("\\s+");

        for (String word : words) {
            word = word.toLowerCase();

            if (count.containsKey(word)) {
                count.put(word, count.get(word) + 1);
            } else {
                count.put(word, 1);
            }
        }

        ArrayList<String> odds = new ArrayList<>();

        for (var entry : count.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                odds.add(entry.getKey());
            }
        }
        System.out.println(String.join(", ", odds));
    }
}
