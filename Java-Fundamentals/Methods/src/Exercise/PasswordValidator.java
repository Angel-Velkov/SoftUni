package Exercise;

import java.util.Scanner;

public class PasswordValidator {
    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();

        boolean isBetween = checkIfIsBetween(input);
        boolean isLetterAndDigit = checkIfIsLetterAndDigit(input);
        boolean hasTwoOrMoreDigits = checkForTwoOrMoreDigits(input);

        if (isBetween && isLetterAndDigit && hasTwoOrMoreDigits) {
            System.out.println("Password is valid");
        }
        if (!isBetween) {
            System.out.println("Password must be between 6 and 10 characters");
        }
        if (!isLetterAndDigit) {
            System.out.println("Password must consist only of letters and digits");
        }
        if (!hasTwoOrMoreDigits) {
            System.out.println("Password must have at least 2 digits");
        }


    }

    private static boolean checkIfIsBetween(String input) {
        return (input.length() >= 6 && input.length() <= 10);
    }

    private static boolean checkIfIsLetterAndDigit(String input) {
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            boolean isDigit = Character.isDigit(symbol);
            boolean isLetter = Character.isLetter(symbol);

            if (!isDigit && !isLetter) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkForTwoOrMoreDigits(String input) {
        int counter = 0;
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            if (symbol >= 48 && symbol <= 57) {
                counter++;
            }
            if (counter == 2) {
                return true;
            }
        }
        return false;
    }
}
