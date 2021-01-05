package Lab;

import java.util.Scanner;

public class MaxNumber {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();
        int max = Integer.MIN_VALUE;

        while (!input.equals("Stop")) {
            int currentNum = Integer.parseInt(input);
            input = scan.nextLine();

            if (currentNum > max) {
                max = currentNum;
            }
        }
        System.out.println(max);
    }
}
