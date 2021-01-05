package Exercise;

import java.util.Scanner;

public class NewHouse {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String flower = scan.nextLine();
        int flowersCount = Integer.parseInt(scan.nextLine());
        int budget = Integer.parseInt(scan.nextLine());
        double price = 0;

        switch (flower) {
            case "Roses":
                if (flowersCount >= 80) {
                    price = 5 * flowersCount * 0.9;
                } else {
                    price = 5 * flowersCount;
                }
                break;
            case "Dahlias":
                if (flowersCount >= 90) {
                    price = 3.8 * flowersCount * 0.85;
                } else {
                    price = 3.8 * flowersCount;
                }
                break;
            case "Tulips":
                if (flowersCount >= 80) {
                    price = 2.8 * flowersCount * 0.85;
                } else {
                    price = 2.8 * flowersCount;
                }
                break;
            case "Narcissus":
                if (flowersCount <= 120) {
                    price = 3 * flowersCount * 1.15;
                } else {
                    price = 3 * flowersCount;
                }
                break;
            case "Gladiolas":
                if (flowersCount <= 80) {
                    price = 2.5 * flowersCount * 1.2;
                } else {
                    price = 2.5 * flowersCount;
                }
                break;
        }
        if (budget >= price) {
            System.out.printf("Hey, you have a great garden with %d %s and %.2f leva left.", flowersCount, flower, (budget - price));
        } else {
            System.out.printf("Not enough money, you need %.2f leva more.", (price - budget));
        }
    }
}
