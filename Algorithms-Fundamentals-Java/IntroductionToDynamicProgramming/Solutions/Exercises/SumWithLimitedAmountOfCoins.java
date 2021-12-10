import java.util.Arrays;
import java.util.Scanner;

public class SumWithLimitedAmountOfCoins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] coins = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int targetSum = scanner.nextInt();

        int[][] memoization = new int[targetSum + 1][coins.length];
        System.out.println(getCount(coins, targetSum, coins.length - 1, memoization));
    }

    private static int getCount(int[] coins, int sum, int current, int[][] memoization) {
        if (sum <= 0 || current < 0) {
            return 0;
        }

        if (memoization[sum][current] > 0) {
            return memoization[sum][current];
        }

        if (coins[current] == sum) {
            memoization[sum][current] = 1;
        }

        int prevNonEqualCoin = current;
        while (prevNonEqualCoin >= 0 && coins[prevNonEqualCoin] == coins[current]) {
            prevNonEqualCoin--;
        }

        memoization[sum][current] +=
                getCount(coins, sum - coins[current], current - 1, memoization)
                        + getCount(coins, sum, prevNonEqualCoin, memoization);

        return memoization[sum][current];
    }
}
