package Exercise;

import java.util.Scanner;

public class MultiplyBigNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String first = scanner.nextLine().replaceFirst("^0+", "");
        int second = Integer.parseInt(scanner.nextLine());

        if (second == 0) {
            System.out.println(0);
            return;
        }

        StringBuilder wholeResult = new StringBuilder();
        int carry = 0;
        for (int i = first.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(String.valueOf(first.charAt(i)));
            int result = digit * second + carry;
            if (i == 0) {
                wholeResult.insert(0, result);
            } else {
                carry = result / 10;
                int restDigit = result % 10;
                wholeResult.insert(0, restDigit);
            }
        }
        System.out.println(wholeResult);
    }
}
