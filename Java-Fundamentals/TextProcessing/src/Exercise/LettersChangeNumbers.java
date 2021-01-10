package Exercise;

import java.util.Scanner;

public class LettersChangeNumbers {
    public static void main(String[] args) {

        String[] tokens = new Scanner(System.in).nextLine().split("\\s+");
        double totalSum = 0;

        for (String s : tokens) {
            double sum = 0;
            char firstLetter = s.charAt(0);
            char secondLetter = s.charAt(s.length() - 1);
            double number = Double.parseDouble(s.substring(1, s.length() - 1));

            int firstLetterPosition = letterPosition(firstLetter);
            int secondLetterPosition = letterPosition(secondLetter);

            if (Character.isUpperCase(firstLetter)) {
                sum += number / firstLetterPosition;
            } else {
                sum += number * firstLetterPosition;
            }

            if (Character.isUpperCase(secondLetter)) {
                sum -= secondLetterPosition;
            } else {
                sum += secondLetterPosition;
            }

            totalSum += sum;
        }

        System.out.printf("%.2f", totalSum);
    }

    private static int letterPosition(char letter) {
        int targetChar = Character.toLowerCase(letter);
        int initialChar = 'a';
        return targetChar - initialChar + 1;
    }
}
