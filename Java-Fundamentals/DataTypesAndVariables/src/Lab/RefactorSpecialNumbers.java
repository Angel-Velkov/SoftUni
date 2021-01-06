package Lab;

import java.util.Scanner;

public class RefactorSpecialNumbers {
    public static void main(String[] args) {

        int n = new Scanner(System.in).nextInt();

        for (int i = 1; i <= n; i++) {
            String currentNum = "" + i;
            int sum = 0;
            for (int j = 0; j < currentNum.length(); j++) {
                int currentChar = Integer.parseInt("" + currentNum.charAt(j));
                sum += currentChar;
            }
            if (sum == 5 || sum == 7 || sum == 11) {
                System.out.println(i + " -> True");
            } else {
                System.out.println(i + " -> False");
            }
        }
    }
}
