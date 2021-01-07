package Exercise;

import java.util.Scanner;

public class SmallestOfThreeNumbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int firstNumber = Integer.parseInt(scan.nextLine());
        int secondNumber = Integer.parseInt(scan.nextLine());
        int thirdNumber = Integer.parseInt(scan.nextLine());

        int smallerNumber = findSmallestNumber(firstNumber, secondNumber);
        int smallestNumber = findSmallestNumber(smallerNumber, thirdNumber);

        System.out.println(smallestNumber);
    }

    private static int findSmallestNumber(int a, int b) {
        if (a < b) {
            return a;
        }
        return b;
    }
}
