import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class KnapsackRecursion {

    private static List<Integer> weights = new ArrayList<>();
    private static List<Integer> values = new ArrayList<>();

    private static int[][] memoization;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int capacity = Integer.parseInt(scanner.nextLine());

        String line;
        while (!"end".equals(line = scanner.nextLine())) {
            int[] tokens = Arrays.stream(line.split("\\s+"))
                    .skip(1)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            weights.add(tokens[0]);
            values.add(tokens[1]);
        }

        memoization = new int[values.size()][capacity + 1];
        for (int[] ints : memoization) {
            Arrays.fill(ints, -1);
        }

        System.out.println("Total Value: " + knapsack(0, 0, capacity));
    }

    private static int knapsack(int weightIndex, int valueIndex, int capacity) {
        if (weightIndex >= weights.size()
                || valueIndex >= values.size()
                || weights.get(weightIndex) > capacity) {
            return 0;
        }

        if (memoization[valueIndex][capacity] != -1) {
            return memoization[valueIndex][capacity];
        }

        return memoization[valueIndex][capacity] = Math.max(
                knapsack(weightIndex + 1, valueIndex + 1, capacity),
                knapsack(weightIndex + 1, valueIndex + 1, capacity - weights.get(weightIndex))
                        + values.get(valueIndex)
        );
    }
}
