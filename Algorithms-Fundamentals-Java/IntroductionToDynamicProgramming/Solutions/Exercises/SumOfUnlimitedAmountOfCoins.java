import java.util.Arrays;
import java.util.Scanner;

public class SumOfUnlimitedAmountOfCoins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] coins = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int targetSum = scanner.nextInt();

        // Each index plays the role of a sum
        // Values are the ways in which we have reached the currents sum
        int[] table = new int[targetSum + 1];
        table[0] = 1;

        for (int coin : coins) {
            for (int currentSum = coin; currentSum <= targetSum; currentSum++) {
                table[currentSum] += table[currentSum - coin];
            }
        }

        System.out.println(table[targetSum]);
    }
}
