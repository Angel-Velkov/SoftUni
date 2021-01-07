package Exercise;

import java.util.Scanner;

public class MiddleCharacters {
    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();

        getMiddleCharacters(input);
    }

    private static void getMiddleCharacters(String input) {
        String middleCharacter = "" + input.charAt(input.length() / 2);

        if (input.length() % 2 == 0) {
            middleCharacter = input.charAt(input.length() / 2 - 1) + middleCharacter;
        }
        System.out.println(middleCharacter);
    }
}
