import java.util.Scanner;

public class BinomialCoefficients {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        System.out.println(getBinomialCoefficient(n, k));
    }

    private static long getBinomialCoefficient(int n, int k) {
        long[] elements = new long[k + 1];

        elements[0] = 1;

        for (int row = 1; row <= n; row++) {
            for (int col = Math.min(row, k); col > 0; col--)
                elements[col] = elements[col] + elements[col - 1];
        }
        return elements[k];
    }
}
