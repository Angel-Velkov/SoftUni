package Exercise;

import java.util.Scanner;

public class EqualSums {
    public static void main(String[] args) {

        String[] numbersAsString = new Scanner(System.in).nextLine().split(" ");
        int[] numbers = new int[numbersAsString.length];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numbersAsString[i]);
        }

        int index = -1;
        for (int i = 0; i < numbers.length ; i++) {

            int leftSum = 0;
            for (int j = 0; j < i; j++) {
                leftSum += numbers[j];
            }

            int rightSum = 0;
            for (int j = i + 1; j < numbers.length; j++) {
                rightSum += numbers[j];
            }

            if (leftSum == rightSum) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            System.out.println(index);
        } else {
            System.out.println("no");
        }
    }
}
