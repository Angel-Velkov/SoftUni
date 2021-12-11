import java.util.Arrays;
import java.util.Scanner;

public class SumWithLimitedAmountOfCoins {
    private static int[] coins;
    private static int[][] table;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        coins = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        int targetSum = scanner.nextInt();

        table = new int[targetSum + 1][coins.length];
        System.out.println(getCount(targetSum, coins.length - 1));
    }

    private static int getCount(int sum, int current) {
        // Out of bounds
        if (sum <= 0 || current < 0) {
            return 0;
        }

        // Existing memo for those coordinates
        if (table[sum][current] > 0) {
            return table[sum][current];
        }

        // The coin is equal to the sum - so there is one way to achieve that
        if (coins[current] == sum) {
            table[sum][current] = 1;
        }

        int prevNonEqualCoin = current;
        while (prevNonEqualCoin >= 0 && coins[prevNonEqualCoin] == coins[current]) {
            prevNonEqualCoin--;
        }

        table[sum][current] +=
                getCount(sum - coins[current], current - 1)
                        + getCount(sum, prevNonEqualCoin);

        return table[sum][current];
    }
}
