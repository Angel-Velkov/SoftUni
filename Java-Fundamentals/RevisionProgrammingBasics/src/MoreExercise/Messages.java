package MoreExercise;

import java.util.Scanner;

public class Messages {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int textLength = Integer.parseInt(scan.nextLine());
        String message = "";

        for (int i = 0; i < textLength; i++) {
            String digits = scan.nextLine();
            int digitLength = digits.length();
            int mainDigit = Integer.parseInt("" + (digits.charAt(0)));
            int offset = (mainDigit - 2) * 3;
            if (mainDigit == 8 || mainDigit == 9) {
                offset += 1;
            }

            int letterIndex = (offset + digitLength - 1);
            char letter = (char) (letterIndex + 97);

            if (mainDigit == 0) {
                letter = (char) (mainDigit + 32);
            }
            message += letter;
        }
        System.out.println(message);
    }
}
