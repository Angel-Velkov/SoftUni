package Lab;

import java.util.Scanner;

public class CleverLily {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int ages = Integer.parseInt(scan.nextLine());
        double washMachine = Double.parseDouble(scan.nextLine());
        int toyPrice = Integer.parseInt(scan.nextLine());
        int money = 0;
        int moneySum = 0;
        int toySum = 0;

        for (int i = 1; i <= ages; i++) {
            if (i % 2 == 0) {
                money += +10;
                moneySum += money - 1;
            } else {
                toySum += toyPrice;
            }
        }
        double totalPrice = toySum + moneySum;
        double difference = Math.abs(totalPrice - washMachine);
        if (washMachine <= totalPrice) {
            System.out.printf("Yes! %.2f", difference);
        } else {
            System.out.printf("No! %.2f", difference);
        }
    }
}
