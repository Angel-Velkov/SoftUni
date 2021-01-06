package MoreExecise;

import java.util.Scanner;

public class FromLeftToTheRight {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < n; i++) {
            int sum = 0;
            String[] input = scan.nextLine().split(" ");
            long firstNum = Long.parseLong(input[0]);
            long secondNum = Long.parseLong(input[1]);

            if (firstNum > secondNum) {
                while (firstNum != 0) {
                    long currentNum = firstNum % 10;
                    firstNum /= 10;

                    sum += Math.abs(currentNum);
                }
            } else {
                while (secondNum != 0) {
                    long currentNum = secondNum % 10;
                    secondNum /= 10;
                    sum += Math.abs(currentNum);
                }
            }
            System.out.println(sum);
        }
    }
}
