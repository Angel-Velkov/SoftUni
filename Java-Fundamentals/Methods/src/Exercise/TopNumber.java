package Exercise;

import java.util.Scanner;

public class TopNumber {
    public static void main(String[] args) {
        int lastNumber = new Scanner(System.in).nextInt();

        for (int i = 1; i <= lastNumber; i++) {
            if (checkIfTheSumIsDivisibleByEight(i) && checkingForOddDigit(i)) {
                System.out.println(i);
            }
        }
    }

    static int sumOfDigits(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    static boolean checkIfTheSumIsDivisibleByEight(int number) {
        int sum = sumOfDigits(number);
        if (sum % 8 == 0) {
            return true;
        }
        return false;
    }

    static boolean checkingForOddDigit(int number) {

        while (number != 0) {
            int currentNum = number % 10;
            if (currentNum % 2 != 0) {
                return true;
            }
            number /= 10;
        }
        return false;
    }
}
