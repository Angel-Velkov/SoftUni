package sorthingAlgorithms;

import java.util.Scanner;

import static utils.ArrayOperations.*;

public class InsertionSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = parseArray(scanner.nextLine(), " ");
        insertionSort(arr);

        print(arr, " ");
    }

    private static void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];

            int j = i - 1;
            while (0 <= j && key < arr[j]) {
                arr[j + 1] = arr[j--];
            }
            arr[j + 1] = key;
        }
    }
}
