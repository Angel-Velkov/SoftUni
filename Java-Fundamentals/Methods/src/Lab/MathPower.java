package Lab;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MathPower {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double number = scan.nextDouble();
        int power = scan.nextInt();

        System.out.println(new DecimalFormat("0.####").format(powerCalculator(number, power)));
    }

    private static double powerCalculator(double number, int power) {
        double result = 1;
        for (int i = 0; i < power; i++) {
            result *= number;
        }
        return result;
    }
}
