package Exercise;

import java.util.Scanner;

public class MagicSum {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] numbersAsString = scan.nextLine().split(" ");
        int[] numbers = new int[numbersAsString.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numbersAsString[i]);
        }
        int magicSum = scan.nextInt();

        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == magicSum) {
                    System.out.println(numbers[i] + " " + numbers[j]);
                }
            }
        }
    }
}
