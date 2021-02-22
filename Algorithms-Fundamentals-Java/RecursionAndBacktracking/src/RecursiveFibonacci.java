import java.util.Scanner;

public class RecursiveFibonacci {

    private static long[] memoization;

    public static void main(String[] args) {

        int n = new Scanner(System.in).nextInt();
        memoization = new long[n + 1];

        System.out.println(fibonacci(n));
    }

    private static long fibonacci(int n) {
        if (n <= 1) {
            return 1;
        }

        if (memoization[n] != 0) {
            return memoization[n];
        }
        memoization[n] = fibonacci(n - 1) + fibonacci(n - 2);

        return memoization[n];
    }
}
