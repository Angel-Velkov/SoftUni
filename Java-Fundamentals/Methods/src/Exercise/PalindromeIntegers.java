package Exercise;

import java.util.Scanner;

public class PalindromeIntegers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        while (!input.equals("END")) {
            if (checkIfIsPalindrome(input)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }

            input = scan.nextLine();
        }
    }

    private static boolean checkIfIsPalindrome(String input) {

        for (int i = 0; i < input.length() / 2; i++) {
            char leftNumber = input.charAt(i);
            char rightNumber = input.charAt(input.length() - (i + 1));

            if (leftNumber != rightNumber) {
                return false;
            }
        }
        return true;
    }
}
