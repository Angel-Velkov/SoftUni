package Lab;

import java.util.Scanner;

public class MinNumber {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();
        int min = Integer.MAX_VALUE;

        while (!input.equals("Stop")) {
            int currentNum = Integer.parseInt(input);
            input = scan.nextLine();

            if (currentNum < min) {
                min = currentNum;
            }
        }
        System.out.println(min);
    }
}
