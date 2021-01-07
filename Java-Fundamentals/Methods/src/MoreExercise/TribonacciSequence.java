package MoreExercise;

import java.util.Scanner;

public class TribonacciSequence {
    public static void main(String[] args) {

        int n = Integer.parseInt(new Scanner(System.in).nextLine());

        printSequenceOfTribonacciSequence(n);
    }

    private static void printSequenceOfTribonacciSequence(int n) {
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {

            if (i == 0 || i == 1){
                sequence[i] = 1;
            } else if (i == 2){
                sequence[i] = 2;
            } else {
                sequence[i] = sequence[i - 1] + sequence[i - 2] + sequence[i - 3];
            }
        }

        for (int i : sequence) {
            System.out.print(i + " ");
        }
    }
}
