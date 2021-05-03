package utils;

import java.util.Arrays;

public class ArrayOperations {
    public static void swap(int[] arr, int firstIndex, int secondIndex) {
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }

    public static int[] parseArray(String line, String separator) {
        return Arrays.stream(line.split(separator))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void print(int[] arr, String delimiter) {
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
