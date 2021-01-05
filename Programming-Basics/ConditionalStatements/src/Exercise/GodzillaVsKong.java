package Exercise;

import java.util.Scanner;

public class GodzillaVsKong {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double budget = Double.parseDouble(scan.nextLine());
        int extraCount = Integer.parseInt(scan.nextLine());
        double priceOfClothing = Double.parseDouble(scan.nextLine());

        double decorPrice = budget * 0.1;
        double clothesPrice = extraCount * priceOfClothing;

        if (extraCount > 150) {
            clothesPrice = clothesPrice * 0.9;
        }
        double totalPrice = clothesPrice + decorPrice;

        if (totalPrice > budget) {
            System.out.println("Not enough money!");
            System.out.printf("Wingard needs %.2f leva more.", Math.abs(totalPrice - budget));
        } else {
            System.out.println("Action!");
            System.out.printf("Wingard starts filming with %.2f leva left.", Math.abs(totalPrice - budget));
        }
    }
}
