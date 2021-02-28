import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CombinationsWithoutRepetition {
    private static int[] numbers;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());
        numbers = new int[k];

        combination(0, 1);
    }

    private static void combination(int index, int start) {
        if (index >= numbers.length) {
            print(numbers);
        } else {
            for (int i = start; i <= n; i++) {
                numbers[index] = i;
                combination(index + 1, i + 1);
            }
        }
    }

    private static void print(int[] numbers) {
        System.out.println(Arrays.stream(numbers)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" ")));
    }
}
