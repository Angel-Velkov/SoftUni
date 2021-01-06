package Exercise;

import java.util.Scanner;

public class SumDigits {
    public static void main(String[] args) {

        int input = new Scanner(System.in).nextInt();
        int sum = 0;
        while (input > 0) {
            sum += input % 10;
            input /= 10;
        }
        System.out.println(sum);
    }
}
