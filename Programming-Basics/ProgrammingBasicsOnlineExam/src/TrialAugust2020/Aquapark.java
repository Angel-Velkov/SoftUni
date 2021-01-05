package TrialAugust2020;

import java.util.Scanner;

public class Aquapark {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String month = scan.nextLine();
        int hours = Integer.parseInt(scan.nextLine());
        int people = Integer.parseInt(scan.nextLine());
        String type = scan.nextLine();
        double sum = 0;

        switch (month) {
            case "march":
            case "april":
            case "may":
                if (type.equals("day")) {
                    sum = 10.5;
                } else if (type.equals("night")) {
                    sum = 8.4;
                }
                break;
            case "june":
            case "july":
            case "august":
                if (type.equals("day")) {
                    sum = 12.6;
                } else if (type.equals("night")) {
                    sum = 10.2;
                }
                break;
        }
        if (people >= 4) {
            sum *= 0.9;
        }
        if (hours >= 5) {
            sum *= 0.5;
        }
        System.out.printf("Price per person for one hour: %.2f%n", sum);
        System.out.printf("Total cost of the visit: %.2f", sum * hours * people);
    }
}
