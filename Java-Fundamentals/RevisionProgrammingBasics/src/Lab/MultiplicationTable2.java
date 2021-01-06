package Lab;

import java.util.Scanner;

public class MultiplicationTable2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int a = Integer.parseInt(scan.nextLine());
        int b = Integer.parseInt(scan.nextLine());

        for (int i = b; i <= 10; i++) {
            int result = a * i;
            System.out.printf("%d X %d = %d%n", a, i, result);
        }
        if (b > 10) {
            System.out.printf("%d X %d = %d%n", a, b, a * b);
        }
    }
}
