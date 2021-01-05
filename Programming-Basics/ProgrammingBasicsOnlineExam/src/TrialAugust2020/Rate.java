package TrialAugust2020;

import java.util.Scanner;

public class Rate {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double money = Double.parseDouble(scan.nextLine());
        int monthCount = Integer.parseInt(scan.nextLine());
        double simpleInterest = money;
        double complexInterest = money;

        for (int i = 1; i <= monthCount; i++) {
            simpleInterest += money * 3 / 100;
            complexInterest += complexInterest * 2.7 / 100;
        }
        double diff = Math.abs(simpleInterest - complexInterest);

        System.out.printf("Simple interest rate: %.2f lv.%n", simpleInterest);
        System.out.printf("Complex interest rate: %.2f lv.%n", complexInterest);

        if (simpleInterest > complexInterest) {
            System.out.printf("Choose a simple interest rate. You will win %.2f lv.", diff);
        } else {
            System.out.printf("Choose a complex interest rate. You will win %.2f lv.", diff);
        }
    }
}
