import java.util.Arrays;
import java.util.Scanner;

public class RodCutting {

    private static int[] prices;
    private static int[] bestPrices;
    private static int[] bestPrevious;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        prices = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int length = scanner.nextInt();
        bestPrices = new int[length + 1];
        bestPrevious = new int[length + 1];

        System.out.println(cutRod(length));
        reconstructSolution(length);
    }

    private static int cutRod(int length) {
        if (length == 0) {
            return 0;
        }

        if (bestPrices[length] != 0) {
            return bestPrices[length];
        }

        int currentBest = bestPrices[length];
        for (int i = 1; i <= length; i++) {
            currentBest = Math.max(currentBest, prices[i] + cutRod(length - i));

            if (currentBest > bestPrices[length]) {
                bestPrices[length] = currentBest;
                bestPrevious[length] = i;
            }
        }
        return bestPrices[length];
    }

    private static int cutRodIterative(int length) {
        for (int i = 1; i <= length; i++) {
            int currentBest;
            for (int j = 1; j <= i; j++) {
                currentBest = Math.max(bestPrices[i], prices[j] + bestPrices[i - j]);
                if (currentBest > bestPrices[i]) {
                    bestPrices[i] = currentBest;
                    bestPrevious[i] = j;
                }
            }
        }
        return bestPrices[length];
    }

    private static void reconstructSolution(int length) {
        while (length - bestPrevious[length] != 0) {
            System.out.print(bestPrevious[length] + " ");
            length = length - bestPrevious[length];
        }
        System.out.println(bestPrevious[length]);
    }
}
