import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NestedLoops {
    private static int[] numbers;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        numbers = new int[n];

        permute(0);
    }

    private static void permute(int index) {
        if (index >= numbers.length) {
            print(numbers);
        } else {
            for (int i = 1; i <= numbers.length; i++) {
                numbers[index] = i;
                permute(index + 1);
            }
        }
    }

    private static void print(int[] numbers) {
        System.out.println(Arrays.stream(numbers)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" ")));
    }
}
