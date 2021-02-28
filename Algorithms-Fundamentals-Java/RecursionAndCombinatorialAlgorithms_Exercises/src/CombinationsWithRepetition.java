import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CombinationsWithRepetition {
    private static int[] numbers;
    private static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        numbers = new int[k];

        combinationsWithRep(0, 1);
    }

    private static void combinationsWithRep(int index, int start) {
        if (index >= numbers.length) {
            print(numbers);
        } else {
            for (int i = start; i <= n; i++) {
                numbers[index] = i;
                combinationsWithRep(index + 1, i);
            }
        }
    }

    private static void print(int[] numbers) {
        System.out.println(Arrays.stream(numbers)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" ")));
    }
}
