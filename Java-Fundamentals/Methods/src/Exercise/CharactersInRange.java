package Exercise;

import java.util.Scanner;

public class CharactersInRange {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        char first = scan.nextLine().charAt(0);
        char second = scan.nextLine().charAt(0);

        if (first < second) {
            printCharactersInRange(first, second);
        } else {
            printCharactersInRange(second, first);
        }
    }

    private static void printCharactersInRange(char smallerChar, char biggerChar) {
        for (int i = smallerChar + 1; i < biggerChar; i++) {
            System.out.print((char) i + " ");
        }
    }
}
