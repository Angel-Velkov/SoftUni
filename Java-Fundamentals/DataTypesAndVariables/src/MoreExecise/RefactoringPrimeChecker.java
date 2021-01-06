package MoreExecise;

import java.util.Scanner;

public class RefactoringPrimeChecker {
    public static void main(String[] args) {

        int n = new Scanner(System.in).nextInt();

        for (int i = 2; i <= n; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            System.out.printf("%d -> %b%n", i, isPrime);
        }
    }
}
