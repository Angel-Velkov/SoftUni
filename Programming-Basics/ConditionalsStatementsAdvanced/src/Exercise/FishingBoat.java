package Exercise;

import java.util.Scanner;

public class FishingBoat {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int budget = Integer.parseInt(scan.nextLine());
        String season = scan.nextLine();
        int fishermenCount = Integer.parseInt(scan.nextLine());
        double totalPrice = 0;

        switch (season) {
            case "Spring":
                totalPrice = 3000;
                break;
            case "Summer":
            case "Autumn":
                totalPrice = 4200;
                break;
            case "Winter":
                totalPrice = 2600;
                break;
        }
        if (fishermenCount <= 6) {
            totalPrice *= 0.9;
        } else if (fishermenCount <= 11) {
            totalPrice *= 0.85;
        } else {
            totalPrice *= 0.75;
        }
        if (fishermenCount % 2 == 0 && !season.equals("Autumn")) {
            totalPrice *= 0.95;
        }
        double difference = Math.abs(budget - totalPrice);
        if (budget >= totalPrice) {
            System.out.printf("Yes! You have %.2f leva left.", difference);
        } else {
            System.out.printf("Not enough money! You need %.2f leva.", difference);
        }
    }
}
