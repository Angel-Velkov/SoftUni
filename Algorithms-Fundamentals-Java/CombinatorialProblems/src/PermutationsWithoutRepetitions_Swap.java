import java.util.Scanner;

public class PermutationsWithoutRepetitions_Swap {
    private static String[] elements;

    public static void main(String[] args) {
        elements = new Scanner(System.in).nextLine().split("\\s+");

        permute(0);
    }

    private static void permute(int index) {
        if (index >= elements.length) {
            print(elements);
        } else {
            permute(index + 1);
            for (int i = index + 1; i < elements.length; i++) {
                swap(elements, index, i);
                permute(index + 1);
                swap(elements, index, i);
            }
        }
    }

    private static void swap(String[] arr, int firstIndex, int secondIndex) {
        String temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }

    private static void print(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
}
