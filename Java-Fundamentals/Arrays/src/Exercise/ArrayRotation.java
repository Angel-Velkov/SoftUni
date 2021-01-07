package Exercise;

import java.util.Scanner;

public class ArrayRotation {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] numbers = scan.nextLine().split(" ");
        int n = scan.nextInt();

        for (int i = 0; i < n % numbers.length; i++) {
            String firstNumber = numbers[0];
            for (int j = 0; j < numbers.length - 1; j++) {
                numbers[j] = numbers[j + 1];
            }
            numbers[numbers.length - 1] = firstNumber;
        }
        System.out.println(String.join(" ", numbers));
    }
}
