import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RecursiveArraySum {
    public static void main(String[] args) throws IOException {
        int[] numbers = Arrays.stream(new BufferedReader(new InputStreamReader(System.in)).readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int sum = sum(numbers, numbers.length - 1);
        System.out.println(sum);
    }

    private static int sum(int[] array, int index) {
        if (index < 0) {
            return 0;
        }

        return array[index] + sum(array, index - 1 );
    }
}
