package Lab;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ReverseStrings {
    public static void main(String[] args) {
        Map<String, StringBuilder> reversedString = new LinkedHashMap<>();
        Scanner scanner = new Scanner(System.in);
        String line;

        while (!"end".equals(line = scanner.nextLine())) {
            StringBuilder reverse = new StringBuilder();
            for (int i = line.length() - 1; i >= 0; i--) {
                reverse.append(line.charAt(i));
            }
            reversedString.put(line, reverse);
        }

        reversedString
                .forEach((k, v) -> System.out.println(k + " = " + v));
    }
}
