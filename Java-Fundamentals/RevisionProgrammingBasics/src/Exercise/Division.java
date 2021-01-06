package Exercise;

import java.util.Scanner;

public class Division {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int input = scan.nextInt();
        int division = 0;

        if (input % 10 == 0) {
            division = 10;
        } else if (input % 7 == 0) {
            division = 7;
        } else if (input % 6 == 0) {
            division = 6;
        } else if (input % 3 == 0) {
            division = 3;
        } else if (input % 2 == 0) {
            division = 2;
        }
        if (division == 0) {
            System.out.println("Not divisible");
        } else {
            System.out.println("The number is divisible by " + division);
        }
    }
}
