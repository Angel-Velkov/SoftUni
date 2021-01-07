package MoreExercise;

import java.util.Scanner;

public class PascalTriangle {
    public static void main(String[] args) {

        int n = new Scanner(System.in).nextInt();
        int[] previousArray = new int[0];
        for (int i = 0; i < n; i++) {
            int[] currentNumbers = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    currentNumbers[j] = 1;
                } else {
                    currentNumbers[j] = previousArray[j] + previousArray[j - 1];
                }
            }
            previousArray = currentNumbers;

            for (int num : currentNumbers) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
