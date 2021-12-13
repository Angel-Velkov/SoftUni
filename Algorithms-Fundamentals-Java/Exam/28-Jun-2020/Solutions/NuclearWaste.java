import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NuclearWaste {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] costs = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int numberOfFlasks = Integer.parseInt(reader.readLine());

        int[] bestPrices = new int[numberOfFlasks + 1];
        int[] prev = new int[numberOfFlasks + 1];

        for (int i = 1; i <= numberOfFlasks; i++) {
            int minCost = Integer.MAX_VALUE;

            for (int j = 1; j <= Math.min(i, costs.length); j++) {

                int currentCost = bestPrices[i - j] + costs[j - 1];

                if (minCost > currentCost) {
                    minCost = currentCost;
                    bestPrices[i] = currentCost;
                    prev[i] = j;
                }
            }
        }

        System.out.println("Cost: " + bestPrices[numberOfFlasks]);

        while (numberOfFlasks > 0) {
            System.out.println(prev[numberOfFlasks] + " => " + bestPrices[prev[numberOfFlasks]]);
            numberOfFlasks -= prev[numberOfFlasks];
        }
    }
}
