package Lab;

import java.util.Scanner;

public class LowerOrUpper {
    public static void main(String[] args) {

        String input = new Scanner(System.in).nextLine();

        if (input.equals(input.toUpperCase())) {
            System.out.println("upper-case");
        } else if (input.equals(input.toLowerCase())) {
            System.out.println("lower-case");
        }
    }
}
