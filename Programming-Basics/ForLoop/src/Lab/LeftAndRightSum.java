package Lab;

import java.util.Scanner;

public class LeftAndRightSum {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        int left = 0;
        int right = 0;

        for (int i = 1; i <= n; i++) {
            left += Integer.parseInt(scan.nextLine());
        }
        for (int i = 1; i <= n; i++) {
            right += Integer.parseInt(scan.nextLine());
        }
        if (left == right) {
            System.out.println("Yes, sum = " + left);
        } else {
            System.out.printf("No, diff = %d", Math.abs(left - right));
        }
    }
}
