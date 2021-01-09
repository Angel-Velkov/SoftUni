package Exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CountCharsInAString {
    public static void main(String[] args) {
        Map<Character, Integer> chars = new LinkedHashMap<>();

        String line = new Scanner(System.in).nextLine();

        for (char symbol : line.toCharArray()) {
            if (symbol != ' ') {
                Integer count = chars.get(symbol);

                if (count == null) {
                    count = 0;
                }
                chars.put(symbol, count + 1);
            }
        }

        chars.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}
