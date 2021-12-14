import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class TwoMinutesToMidnight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        System.out.println(calculateBinomialCoefficient(n, k));
    }

    private static BigInteger calculateBinomialCoefficient(int n, int k) {
        k++;

        BigInteger[] array = new BigInteger[k + 1];
        Arrays.fill(array, BigInteger.valueOf(0));

        array[0] = BigInteger.valueOf(1);

        for (int row = 1; row <= n; row++) {
            for (int col = Math.min(row, k); col > 0; col--) {
                array[col] = array[col].add(array[col - 1]);
            }
        }

        return array[k - 1];
    }
}
