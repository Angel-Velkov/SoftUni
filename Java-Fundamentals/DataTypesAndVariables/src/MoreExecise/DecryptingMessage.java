package MoreExecise;

import java.util.Scanner;

public class DecryptingMessage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int key = Integer.parseInt(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char currentChar = scanner.nextLine().charAt(0);
            char newChar = (char) ((int) currentChar + key);
            word.append(newChar);
        }
        System.out.println(word);
    }
}
