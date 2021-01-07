package Lab;

import java.util.Scanner;

public class CalculateRectangleArea {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double wight = scan.nextDouble();
        double height = scan.nextDouble();

        System.out.println(calculatesRectahgleArea(wight, height));
    }

    private static double calculatesRectahgleArea(double a, double b) {
        return a * b;
    }
}
