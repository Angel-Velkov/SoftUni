package sorthingAlgorithms;

import java.util.Arrays;
import java.util.Scanner;

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

    private static void swap(int[] arr, int firstIndex, int secondIndex) {
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }

    private static int[] parseArray(String line, String separator) {
        return Arrays.stream(line.split(separator))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void print(int[] arr, String delimiter) {
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            out.append(arr[i]);
            if (i + 1 < arr.length) {
                out.append(delimiter);
            }
        }
        System.out.println(out);
    }
}
