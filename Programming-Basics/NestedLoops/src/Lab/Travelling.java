package Lab;

import java.util.Scanner;

public class Travelling {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String destination = scan.nextLine();

        while (!destination.equals("End")) {
            double moneyNeeded = Integer.parseInt(scan.nextLine());
            double sum = 0;

            while (moneyNeeded > sum) {
                double savings = Double.parseDouble(scan.nextLine());
                sum += savings;
            }
            System.out.printf("Going to %s!%n", destination);
            destination = scan.nextLine();
        }
    }
}
