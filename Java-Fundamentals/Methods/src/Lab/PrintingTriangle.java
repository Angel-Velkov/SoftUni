package Lab;

import java.util.Scanner;

public class PrintingTriangle {
    public static void main(String[] args) {
        int maxLineLength = new Scanner(System.in).nextInt();

        for (int i = 0; i <= maxLineLength; i++) {
            printLineOfIncreasingNumbers(1, i);
        }

        for (int i = maxLineLength - 1; i >= 1; i--) {
            printLineOfIncreasingNumbers(1, i);
        }

    }

    static void printLineOfIncreasingNumbers(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(i);
            if (i < end) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
