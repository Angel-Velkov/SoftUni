import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SuperSet {

    private static int[] numbers;
    private static int[] slots;

    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        numbers = Arrays.stream(reader.readLine().split("\\s*,\\s*")).mapToInt(Integer::parseInt).toArray();

        for (int k = 0; k <= numbers.length; k++) {
            slots = new int[k];
            combinations(0, 0);
        }

        System.out.println(System.lineSeparator() + result.toString().trim());
    }

    private static void combinations(int index, int start) {
        if (index >= slots.length) {
            addToTheResult(slots);
        } else {
            for (int i = start; i < numbers.length; i++) {
                slots[index] = numbers[i];
                combinations(index + 1, i + 1);
            }
        }
    }

    private static void addToTheResult(int[] arr) {
        result.append(
                Arrays.stream(arr)
                        .boxed()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        ).append(System.lineSeparator());
    }
}
