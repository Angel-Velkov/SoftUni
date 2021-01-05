package Exercise;

import java.util.Scanner;

public class CharityCampaign {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int days = Integer.parseInt(scan.nextLine());
        int confectioners = Integer.parseInt(scan.nextLine());
        int cakes = Integer.parseInt(scan.nextLine());
        int waffles = Integer.parseInt(scan.nextLine());
        int pancakes = Integer.parseInt(scan.nextLine());

        double cakesPrice = cakes * 45;
        double wafflesPrice = waffles * 5.8;
        double pancakesPrice = pancakes * 3.2;

        double totalSum = (cakesPrice + wafflesPrice + pancakesPrice) * confectioners * days;

        double donatedSum = totalSum - totalSum / 8;

        System.out.printf("%.2f", donatedSum);
    }
}
