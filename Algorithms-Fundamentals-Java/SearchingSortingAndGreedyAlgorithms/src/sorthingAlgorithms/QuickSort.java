package sorthingAlgorithms;

import java.util.Scanner;

import static utils.ArrayOperations.*;

public class QuickSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = parseArray(scanner.nextLine(), " ");
        sort(arr);

        print(arr, " ");
    }

    private static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static int partition(int[] a, int i, int j) {
        int p = a[i]; // p is the pivot
        int m = i; // S1 and S2 are initially empty
        for (int k = i + 1; k <= j; k++) { // explore the unknown region
            if (a[k] < p) { // case 2
                m++;
                swap(a, k, m);
            } // notice that we do nothing in case 1: a[k] >= p
        }
        swap(a, i, m); // final step, swap pivot with a[m]
        return m; // return the index of pivot
    }

    private static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int m = partition(a, low, high); // O(N)
            // a[low..high] ~> a[low..m–1], pivot, a[m+1..high]
            quickSort(a, low, m - 1); // recursively sort left subarray
            // a[m] = pivot is already sorted after partition
            quickSort(a, m + 1, high); // then sort right subarray
        }
    }
}
