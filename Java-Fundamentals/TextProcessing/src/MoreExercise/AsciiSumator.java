package MoreExercise;

import java.util.Scanner;

public class AsciiSumator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstChar = scanner.nextLine().charAt(0);
        int secondChar = scanner.nextLine().charAt(0);
        String line = scanner.nextLine();
        int sum = 0;
        for (int i = 0; i < line.length(); i++) {
            int currentChar = line.charAt(i);
            if (currentChar > Math.min(firstChar, secondChar) && currentChar < Math.max(firstChar, secondChar)) {
                sum += currentChar;
            }
        }
        System.out.println(sum);
    }
}
