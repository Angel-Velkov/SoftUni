package Exercise;

import java.util.Scanner;

public class SumPrimeNonPrime {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();
        int primeSum = 0;
        int nonPrimeSum = 0;

        while (!input.equals("stop")) {
            int currentNum = Integer.parseInt(input);
            boolean isPrime = true;

            if (currentNum < 0) {
                System.out.println("Number is negative.");
            } else {
                for (int i = 2; i < currentNum; i++) {
                    if (currentNum % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    primeSum += currentNum;
                } else {
                    nonPrimeSum += currentNum;
                }
            }

            input = scan.nextLine();
        }
        System.out.println("Sum of all prime numbers is: " + primeSum);
        System.out.println("Sum of all non prime numbers is: " + nonPrimeSum);
    }
}
