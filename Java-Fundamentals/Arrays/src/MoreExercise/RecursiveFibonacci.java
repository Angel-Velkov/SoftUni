package MoreExercise;

import java.util.Scanner;

public class RecursiveFibonacci {
    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();

        int[] fibonacci = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0 || i == 1) {
                fibonacci[i] = 1;
            } else {
                fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
            }
        }
        if (n > 0) {
            System.out.println(fibonacci[n - 1]);
        } else {
            System.out.println(n);
        }
    }
}
