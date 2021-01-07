package Lab;

import java.util.Scanner;

public class Grades {
    public static void main(String[] args) {
        Grade(Double.parseDouble(new Scanner(System.in).nextLine()));
    }

    private static void Grade(double number) {
        if (number < 3) {
            System.out.println("Fail");
        } else if (number < 3.5) {
            System.out.println("Poor");
        } else if (number < 4.5) {
            System.out.println("Good");
        } else if (number < 5.5) {
            System.out.println("Very good");
        } else {
            System.out.println("Excellent");
        }
    }
}
