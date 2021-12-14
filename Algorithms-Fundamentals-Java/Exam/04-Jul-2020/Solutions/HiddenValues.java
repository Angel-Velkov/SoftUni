import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class HiddenValues {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] numbers = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] sums = new int[numbers.length];
        sums[0] = numbers[0];
        int maxSum = sums[0];
        int index = 0;

        int[] prev = new int[numbers.length];
        Arrays.fill(prev, -1);

        for (int i = 1; i < numbers.length; i++) {
            if (sums[i - 1] + numbers[i] > numbers[i]) {
                sums[i] = sums[i - 1] + numbers[i];
                prev[i] = i - 1;
            } else {
                sums[i] = numbers[i];
            }

            if (sums[i] > maxSum) {
                maxSum = sums[i];
                index = i;
            }
        }

        Deque<Integer> result = new ArrayDeque<>();

        while (index > -1) {
            result.push(numbers[index]);
            index = prev[index];
        }

        System.out.println(maxSum);
        result.forEach(n -> System.out.print(n + " "));

        // Author solution
        /*
        int maxSum = Integer.MIN_VALUE;
        int currentBestSum = 0;
        int length = 0;
        int start = 0;
        int end = 0;

        for (int i = 0; i < numbers.length; i++) {
            currentBestSum += numbers[i];

            if (maxSum < currentBestSum) {
                maxSum = currentBestSum;
                start = length;
                end = i;
            }

            if (currentBestSum < 0) {
                currentBestSum = 0;
                length = i + 1;
            }
        }

        System.out.println(maxSum);
        for (int i = start; i <= end; i++) {
            System.out.print(numbers[i] + " ");
        }
        */
    }
}
