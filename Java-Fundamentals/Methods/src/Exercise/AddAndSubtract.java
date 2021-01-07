package Exercise;

import java.util.Scanner;

public class AddAndSubtract {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int first = Integer.parseInt(scan.nextLine());
        int second = Integer.parseInt(scan.nextLine());
        int third = Integer.parseInt(scan.nextLine());

        int sum = getSum(first, second);
        int result = subtractTwoNumbers(sum, third);
        System.out.println(result);
    }

    static int getSum(int a, int b) {
        return a + b;
    }

    static int subtractTwoNumbers(int a, int b) {
        return a - b;
    }
}
