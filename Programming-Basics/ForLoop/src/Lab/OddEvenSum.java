package Lab;

import java.util.Scanner;

public class OddEvenSum {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        int odd = 0;
        int even = 0;

        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                even += Integer.parseInt(scan.nextLine());
            } else {
                odd += Integer.parseInt(scan.nextLine());
            }
        }
        if (even == odd) {
            System.out.println("Yes");
            System.out.println("Sum = " + even);
        } else {
            System.out.println("No");
            System.out.println("Diff = " + Math.abs(even - odd));
        }
    }
}
