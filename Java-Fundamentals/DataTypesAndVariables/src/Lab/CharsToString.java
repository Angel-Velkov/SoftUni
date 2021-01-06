package Lab;

import java.util.Scanner;

public class CharsToString {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String firstChar = scan.nextLine();
        String secondChar = scan.nextLine();
        String thirdChar = scan.nextLine();

        System.out.println(firstChar + secondChar + thirdChar);
    }
}
