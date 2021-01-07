package Lab;

import java.util.Scanner;

public class MultiplyEvensByOdds {
    public static void main(String[] args) {
        int number = new Scanner(System.in).nextInt();

        System.out.println(getMultipleOfEvensAndOdds(number));
    }

    private static int getMultipleOfEvensAndOdds(int number) {
        int evenSum = getSumOfEvenDigits(number);
        int oddSum = getSumOfOddDigits(number);

        return evenSum * oddSum;

    }

    private static int getSumOfEvenDigits(int n) {
        int evenSum = 0;
        while (n > 0) {
            if (n % 2 == 0) {
                evenSum += n % 10;
            }
            n /= 10;
        }
        return evenSum;
    }

    private static int getSumOfOddDigits(int n) {
        int oddSum = 0;
        while (n > 0) {
            if (n % 2 != 0) {
                oddSum += n % 10;
            }
            n /= 10;
        }
        return oddSum;
    }

}
