package Lab;

import java.util.Scanner;

public class SignOfInteger {
    public static void main(String[] args) {
        printSign(Integer.parseInt(new Scanner(System.in).nextLine()));
    }

    static void printSign(int number) {
        if (number > 0) {
            System.out.printf("The number %d is positive.", number);
        } else if (number < 0) {
            System.out.printf("The number %d is negative.", number);
        } else {
            System.out.printf("The number %d is zero.", number);
        }
    }
}
