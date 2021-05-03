package sorthingAlgorithms;

import java.util.Scanner;

import static utils.ArrayOperations.*;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = parseArray(scanner.nextLine(), " ");
        bubbleSort(arr);

        print(arr, ", ");
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }
}
