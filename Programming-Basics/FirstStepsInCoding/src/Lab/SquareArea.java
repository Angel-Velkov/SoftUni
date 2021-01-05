package Lab;

import java.util.Scanner;

public class SquareArea {
    public static void main(String[] args) {
        Scanner scan = new java.util.Scanner(System.in);
        String input = scan.nextLine();
        int num = Integer.parseInt(input);
        int result = num * num;
        System.out.println(result);
    }
}
