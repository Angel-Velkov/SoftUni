import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class PermutationsWithRepetitions_Optimized {
    public static void main(String[] args) {

        //{1, 3, 5, 5, 5}
        int[] arr = Arrays.stream(new Scanner(System.in).nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.sort(arr);
        permuteRep(arr, 0, arr.length - 1);
    }

    private static void permuteRep(int[] arr, int start, int end) {
        print(arr);
        for (int left = end - 1; left >= start; left--) {
            for (int right = left + 1; right <= end; right++) {
                if (arr[left] != arr[right]) {
                    swap(arr, left, right);
                    permuteRep(arr, left + 1, end);
                }
            }
            int firstElement = arr[left];
            for (int i = left; i <= end - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr[end] = firstElement;
        }
    }

    private static void swap(int[] arr, int firstIndex, int secondIndex) {
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }

    private static void print(int[] arr) {
        Iterator<Integer> iterator = Arrays.stream(arr).iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
            if (iterator.hasNext()) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
