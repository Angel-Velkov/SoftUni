package Lab;

import java.util.Scanner;

public class CharacterSequence {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String text = scan.nextLine();
        int symbols = text.length();
        for (int i = 0; i < symbols; i++) {
            char letter = text.charAt(i);
            System.out.println(letter);
        }
    }
}
