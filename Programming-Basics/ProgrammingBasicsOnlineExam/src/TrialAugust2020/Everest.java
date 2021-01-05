package TrialAugust2020;

import java.util.Scanner;

public class Everest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String choice = scan.nextLine();
        int counter = 1;
        int start = 5364;
        int end = 8848;

        while (!choice.equals("END")) {
            int meters = Integer.parseInt(scan.nextLine());
            if (choice.equals("Yes")) {
                counter++;
                start += meters;
            } else {
                start += meters;
            }
            if (start >= end) {
                System.out.printf("Goal reached for %d days!", counter);
                return;
            }
            if (counter == 5) {
                break;
            }
            choice = scan.nextLine();
        }
        System.out.printf("Failed!%n%d", start);
    }
}
