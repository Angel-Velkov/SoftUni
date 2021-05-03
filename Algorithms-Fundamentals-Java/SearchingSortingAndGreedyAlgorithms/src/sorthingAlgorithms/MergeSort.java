package sorthingAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static utils.ArrayOperations.*;

public class MergeSort {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = parseArray(reader.readLine(), " ");
        sort(arr);

        print(arr, " ");
    }

    private static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }

        int mid = (begin + end) / 2;

        mergeSort(arr, begin, mid);
        mergeSort(arr, mid + 1, end);

        merge(arr, begin, mid, end);
    }

    private static void merge(int[] arr, int begin, int mid, int end) {
        if (mid < 0 || mid >= arr.length || arr[mid] <= arr[mid + 1]) {
            return;
        }

        int[] helper = new int[arr.length];

        if (end + 1 - begin >= 0) {
            System.arraycopy(arr, begin, helper, begin, end + 1 - begin);
        }

        int leftPointer = begin;
        int rightPointer = mid + 1;

        for (int i = begin; i <= end; i++) {
            if (leftPointer > mid) {
                arr[i] = helper[rightPointer++];
            } else if (rightPointer > end) {
                arr[i] = helper[leftPointer++];
            } else if (helper[leftPointer] > helper[rightPointer]) {
                arr[i] = helper[rightPointer++];
            } else if (helper[leftPointer] <= helper[rightPointer]) {
                arr[i] = helper[leftPointer++];
            }
        }
    }
}
