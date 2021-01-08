package MoreExercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Messaging {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        List<Character> textAsChars = getStringAsChars(scanner);
        List<Character> takenChars = new ArrayList<>();

        for (int i = 0; i < numbers.length; i++) {
            int sum = 0;
            while (numbers[i] != 0) {
                sum += numbers[i] % 10;
                numbers[i] = numbers[i] / 10;
            }

            while (sum >= textAsChars.size()) {
                sum -= textAsChars.size();
            }
            if (textAsChars.isEmpty()) {
                break;
            } else {
                takenChars.add(textAsChars.remove(sum));
            }
        }

        for (char c : takenChars) {
            System.out.print(c);
        }
    }

    private static List<Character> getStringAsChars(Scanner scanner) {
        String text = scanner.nextLine();
        List<Character> characters = new ArrayList<>(text.length());
        for (int i = 0; i < text.length(); i++) {
            characters.add(text.charAt(i));
        }
        return characters;
    }
}

/*
0123456789012345678901234567
This is some message for you
29 - 28 = 1 -> h
012345678901234567890123456
Tis is some message for you
13 - > e
01234567890123456789012345
Tis is some mssage for you
23 -> y
 */
