package Lab;

import java.util.Scanner;

public class EvenAndOddSubtraction {
    public static void main(String[] args) {

        String input = new Scanner(System.in).nextLine();
        String[] numbersAsString = input.split(" ");
        int[] numbers = new int[numbersAsString.length];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numbersAsString[i]);
        }
        int evenSum = 0;
        int oddSum = 0;

        for (int number : numbers) {
            if (number % 2 == 0) {
                evenSum += number;
            } else {
                oddSum += number;
            }
        }

        System.out.println(evenSum - oddSum);
    }
}
