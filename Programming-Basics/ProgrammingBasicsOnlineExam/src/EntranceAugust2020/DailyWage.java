package EntranceAugust2020;

import java.util.Scanner;

public class DailyWage {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int rows = Integer.parseInt(scan.nextLine());
        int position = Integer.parseInt(scan.nextLine());
        double berriesPrice = 0;
        double blueberriesPrice = 0;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= position; j++) {
                if (i % 2 != 0) {
                    berriesPrice += 3.5;
                }
                if (i % 2 == 0 && j % 3 != 0) {
                    berriesPrice += 5;
                }
            }
        }
        double totalPrice = (berriesPrice + blueberriesPrice) * 0.8;

        System.out.printf("Total: %.2f lv.", totalPrice);
    }
}
