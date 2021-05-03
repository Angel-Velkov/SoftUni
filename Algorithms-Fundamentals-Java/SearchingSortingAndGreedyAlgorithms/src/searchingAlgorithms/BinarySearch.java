package searchingAlgorithms;

import java.util.Scanner;

import static utils.ArrayOperations.parseArray;

public class BinarySearch {
    private final static int KEY_NOT_FOUND = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = parseArray(scanner.nextLine(), " ");
        int key = scanner.nextInt();

        int element = binarySearch(arr, key);
        System.out.println(element);
    }

    public static int binarySearch(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        while (end >= start) {
            int mid = (start + end) / 2;

            if (key > arr[mid]) {
                start = mid + 1;
            } else if (key < arr[mid]) {
                end = mid - 1;
            } else {
                return mid;
            }
        }

        return KEY_NOT_FOUND;
    }
}
