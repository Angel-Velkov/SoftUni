package Lab;

import java.util.Scanner;

public class ToyShop {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double excursion = Double.parseDouble(scan.nextLine());
        int puzzle = Integer.parseInt(scan.nextLine());
        int doll = Integer.parseInt(scan.nextLine());
        int teddyBear = Integer.parseInt(scan.nextLine());
        int minions = Integer.parseInt(scan.nextLine());
        int truck = Integer.parseInt(scan.nextLine());

        double puzzlePrice = 2.6 * puzzle;
        double dollPrice = 3 * doll;
        double teddyBearPrice = 4.1 * teddyBear;
        double minionsPrice = 8.2 * minions;
        double truckPrice = 2 * truck;

        double total = puzzlePrice + dollPrice + teddyBearPrice + minionsPrice + truckPrice;
        int toys = puzzle + doll + teddyBear + minions + truck;

        if (toys >= 50) {
            total = total * 0.75;
        }
        total = total * 0.9;
        if (total >= excursion) {
            System.out.printf("Yes! %.2f lv left.", Math.abs(total - excursion));
        } else {
            System.out.printf("Not enough money! %.2f lv needed.", Math.abs(excursion - total));
        }
    }
}
