package Lab;

import java.util.Scanner;

public class RepeatStrings {
    public static void main(String[] args) {
        String[] words = new Scanner(System.in).nextLine().split("\\s+");

        StringBuilder repeats = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String r = repeat(words[i], words[i].length());
            repeats.append(r);
        }
        System.out.println(repeats);
    }

    private static String repeat(String word, int length) {
        StringBuilder repeatedWord = new StringBuilder();
        for (int i = 0; i < length; i++) {
            repeatedWord.append(word);
        }
        return repeatedWord.toString();
    }
}
