package EntranceAugust2020;

import java.util.Scanner;

public class ShopingForSchool {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int pencilsCount = Integer.parseInt(scan.nextLine());
        int feltTipPensCount = Integer.parseInt(scan.nextLine());
        int sketchbooksCount = Integer.parseInt(scan.nextLine());
        int notebooksCount = Integer.parseInt(scan.nextLine());

        double pencilsPrice = 4.75 * pencilsCount;
        double feltTipPensPrice = 1.8 * feltTipPensCount;
        double sketchbooksPrice = 6.5 * sketchbooksCount;
        double notebooksPrice = 2.5 * notebooksCount;

        double totalPrice = pencilsPrice + feltTipPensPrice + sketchbooksPrice + notebooksPrice;
        double discount = totalPrice - (totalPrice * 5 / 100);

        System.out.printf("Your total is %.2flv", discount);
    }
}
