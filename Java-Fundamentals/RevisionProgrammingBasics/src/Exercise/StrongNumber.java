package Exercise;

import java.util.Scanner;

public class StrongNumber {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int inputNumber = scan.nextInt();
        int checkNumber = inputNumber;
        int sum = 0;

        while (checkNumber > 0) {
            int currentNum = checkNumber % 10;
            checkNumber /= 10;
            int factorial = 1;
            for (int i = currentNum; i > 0; i--) {
                factorial *= i;
            }
            sum += factorial;
        }
        if (inputNumber == sum) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
