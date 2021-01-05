package EntranceAugust2020;

import java.util.Scanner;

public class KartCenter {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double enteredSum = Double.parseDouble(scan.nextLine());
        String tours = scan.nextLine();
        String fenCard = scan.nextLine();
        String type = scan.nextLine();
        double totalPrice = 0;

        if (tours.equals("five")) {
            switch (type) {
                case "Child":
                    totalPrice = 7;
                    break;
                case "Junior":
                    totalPrice = 9;
                    break;
                case "Adult":
                    totalPrice = 12;
                    break;
                case "Profi":
                    totalPrice = 18;
                    break;
            }
        } else if (tours.equals("ten")) {
            switch (type) {
                case "Child":
                    totalPrice = 11;
                    break;
                case "Junior":
                    totalPrice = 16;
                    break;
                case "Adult":
                    totalPrice = 21;
                    break;
                case "Profi":
                    totalPrice = 32;
                    break;
            }
        }
        if (fenCard.equals("yes")) {
            totalPrice *= 0.8;
        }
        double diff = Math.abs(totalPrice - enteredSum);

        if (enteredSum >= totalPrice) {
            System.out.printf("You bought %s ticket for %s laps. Your change is %.2flv.", type, tours, diff);
        } else {
            System.out.printf("You don't have enough money! You need %.2flv more.", diff);
        }
    }
}
