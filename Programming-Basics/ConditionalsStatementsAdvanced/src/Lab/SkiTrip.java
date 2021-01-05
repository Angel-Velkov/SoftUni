package Lab;

import java.util.Scanner;

public class SkiTrip {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int days = Integer.parseInt(scan.nextLine());
        String room = scan.nextLine();
        String evaluation = scan.nextLine();

        double totalPrice = 0;
        int nights = days - 1;

        switch (room) {
            case "room for one person":
                totalPrice = nights * 18;
                break;
            case "apartment":
                if (nights < 10) {
                    totalPrice = (nights * 25) * 0.7;
                } else if (nights <= 15) {
                    totalPrice = (nights * 25) * 0.65;
                } else {
                    totalPrice = (nights * 25 * 1.0) / 2;
                }
                break;
            case "president apartment":
                if (nights < 10) {
                    totalPrice = (nights * 35) * 0.9;
                } else if (nights <= 15) {
                    totalPrice = (nights * 35) * 0.85;
                } else {
                    totalPrice = (nights * 35) * 0.8;
                }
                break;
        }
        if (evaluation.equals("positive")) {
            totalPrice *= 1.25;
        } else if (evaluation.equals("negative")) {
            totalPrice *= 0.9;
        }
        System.out.printf("%.2f", totalPrice);
    }
}
