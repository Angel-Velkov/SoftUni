import java.util.Scanner;

public class Fibonacci {

    private static long[] memoization;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();
        memoization = new long[number + 1];

        System.out.println(fib(number));
    }

    private static long fib(int number) {
        if (number <= 2) {
            return 1;
        }

        if (memoization[number] != 0) {
            return memoization[number];
        }

        memoization[number] = fib(number - 1) + fib(number - 2);

        return memoization[number];
    }
}
