import java.util.HashSet;
import java.util.Scanner;

public class PermutationsWithRepetitions {
    private static String[] elements;

    public static void main(String[] args) {
        elements = new Scanner(System.in).nextLine().split("\\s+");

        permuteWithRepetitions(0);
    }


    private static void permuteWithRepetitions(int index) {
        if (index >= elements.length) {
            print(elements);
        } else {
            HashSet<String> swapped = new HashSet<>();
            swapped.add(elements[index]);
            permuteWithRepetitions(index + 1);
            for (int i = index + 1; i < elements.length; i++) {
                if (!swapped.contains(elements[i])) {
                    swap(elements, index, i);
                    permuteWithRepetitions(index + 1);
                    swap(elements, index, i);
                    swapped.add(elements[i]);
                }
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
