package Exercise;

import java.util.Scanner;

public class VowelsCount {
    public static void main(String[] args) {

        String input = new Scanner(System.in).nextLine();

        vowelsCounter(input);
    }

    private static void vowelsCounter(String text) {
        int vowelsCount = 0;
        for (int i = 0; i < text.length(); i++) {
            char currentLetter = text.toLowerCase().charAt(i);
            switch (currentLetter) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    vowelsCount++;
            }
        }
        System.out.println(vowelsCount);
    }
}
