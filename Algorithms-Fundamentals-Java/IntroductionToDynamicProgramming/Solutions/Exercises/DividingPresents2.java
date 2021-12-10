import java.util.Arrays;
import java.util.Scanner;

public class DividingPresents2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] presents = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int totalSum = Arrays.stream(presents).sum();
        int[] sums = new int[totalSum + 1];
        Arrays.fill(sums, -1);
        sums[0] = 0;

        for (int presentIndex = 0; presentIndex < presents.length; presentIndex++) {
            int presentValue = presents[presentIndex];

            for (int prevSumIndex = totalSum - presentValue; prevSumIndex >= 0; prevSumIndex--) {
                if (sums[prevSumIndex] != -1 && sums[prevSumIndex + presentValue] == -1) {
                    sums[prevSumIndex + presentValue] = presentIndex;
                }
            }
        }

        int median = totalSum / 2;

        for (int i = median; i >= 0; i--) {
            if (sums[i] != -1) {
                System.out.println("Difference: " + (totalSum - i - i));
                System.out.println("Alan:" + i + " Bob:" + (totalSum - i));
                System.out.print("Alan takes:");
                while (i != 0) {
                    System.out.print(" " + presents[sums[i]]);
                    i -= presents[sums[i]];
                }
                System.out.println();
                System.out.println("Bob takes the rest.");
            }
        }
    }
}
