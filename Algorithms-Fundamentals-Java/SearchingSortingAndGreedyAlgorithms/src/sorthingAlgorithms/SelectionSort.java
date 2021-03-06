package sorthingAlgorithms;

import java.util.Scanner;

import static utils.ArrayOperations.*;

public class SelectionSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = parseArray(scanner.nextLine(), " ");
        selectionSort(arr);

        print(arr, ", ");
    }

    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }
}
