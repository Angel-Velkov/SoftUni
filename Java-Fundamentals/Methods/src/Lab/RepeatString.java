package Lab;

import java.util.Scanner;

public class RepeatString {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String s = scan.nextLine();
        int repetitionsCount = scan.nextInt();

        String repeated = repeats(s, repetitionsCount);
        System.out.println(repeated);
    }

    private static String repeats(String s, int repetitionsCount) {
        String[] text = new String[repetitionsCount];

        for (int i = 0; i < repetitionsCount; i++) {
            text[i] = s;
        }
        return String.join("", text);
    }
}
