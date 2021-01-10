package Lab;

import java.util.Scanner;

public class DigitsLettersAndOther {
    public static void main(String[] args) {
        String line = new Scanner(System.in).nextLine();

        StringBuilder digits = new StringBuilder();
        StringBuilder letters = new StringBuilder();
        StringBuilder characters = new StringBuilder();

        char[] symbols = line.toCharArray();
        for (int i = 0; i < line.length(); i++) {
            char currentSymbol = symbols[i];

            if (Character.isDigit(currentSymbol)){
                digits.append(currentSymbol);
            } else if (Character.isLetter(currentSymbol)){
                letters.append(currentSymbol);
            } else {
                characters.append(currentSymbol);
            }
        }

        System.out.println(digits);
        System.out.println(letters);
        System.out.println(characters);
    }
}
