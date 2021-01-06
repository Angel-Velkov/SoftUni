package Lab;

import java.util.Scanner;

public class SpecialNumbers {
    public static void main(String[] args) {

        int n = new Scanner(System.in).nextInt();

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            int number = i;

            while (number > 0) {
                sum += number % 10;
                number /= 10;
            }
            if (sum == 5 || sum == 7 || sum == 11) {
                System.out.println(i + " -> True");
            } else {
                System.out.println(i + " -> False");
            }
        }
    }
}
