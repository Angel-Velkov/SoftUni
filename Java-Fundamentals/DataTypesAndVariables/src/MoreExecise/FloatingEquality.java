package MoreExecise;

import java.util.Scanner;

public class FloatingEquality {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double a = scan.nextDouble();
        double b = scan.nextDouble();
        final float precision = 0.000001f;

        if (Math.abs(a - b) > precision) {
            System.out.println("False");
        } else {
            System.out.println("True");
        }
    }
}
