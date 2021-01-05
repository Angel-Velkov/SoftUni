package Exercise;

import java.util.Scanner;

public class OddOrEvenPosition {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        double oddMin = 1000000000.0;
        double oddMax = -1000000000.0;
        double odd = 0;
        double evenMin = 1000000000.0;
        double evenMax = -1000000000.0;
        double even = 0;

        for (int i = 1; i <= n; i++) {
            double current = Double.parseDouble(scan.nextLine());

            if (i % 2 != 0) {
                odd += current;
                if (current > oddMax) {
                    oddMax = current;
                }
                if (current < oddMin) {
                    oddMin = current;
                }
            } else {
                even += current;
                if (current > evenMax) {
                    evenMax = current;
                }
                if (current < evenMin) {
                    evenMin = current;
                }
            }
        }
        System.out.printf("OddSum=%.2f,%n", odd);
        System.out.print("OddMin=");
        if (oddMin != 1000000000.0) {
            System.out.printf("%.2f,%n", oddMin);
        } else {
            System.out.println("No,");
        }
        System.out.print("OddMax=");
        if (oddMax != -1000000000.0) {
            System.out.printf("%.2f,%n", oddMax);
        } else {
            System.out.println("No,");
        }
        System.out.printf("EvenSum=%.2f,%n", even);
        System.out.print("EvenMin=");
        if (evenMin != 1000000000.0) {
            System.out.printf("%.2f,%n", evenMin);
        } else {
            System.out.println("No,");
        }
        System.out.print("EvenMax=");
        if (evenMax != -1000000000.0) {
            System.out.printf("%.2f%n", evenMax);
        } else {
            System.out.println("No");
        }
    }
}
