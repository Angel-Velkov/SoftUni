package Exercise;

import java.util.Scanner;

public class RageExpenses {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int lostGamesCount = Integer.parseInt(scan.nextLine());
        double headsetPrice = Double.parseDouble(scan.nextLine());
        double mousePrice = Double.parseDouble(scan.nextLine());
        double keyboardPrice = Double.parseDouble(scan.nextLine());
        double displayPrice = Double.parseDouble(scan.nextLine());

        double headset = Math.floor(lostGamesCount * 1.0 / 2) * headsetPrice;
        double mouse = Math.floor(lostGamesCount * 1.0 / 3) * mousePrice;
        double keyboard = Math.floor(lostGamesCount * 1.0 / 6) * keyboardPrice;
        double display = Math.floor(lostGamesCount * 1.0 / 12) * displayPrice;

        double totalPrice = headset + mouse + keyboard + display;

        System.out.printf("Rage expenses: %.2f lv.", totalPrice);
    }
}
