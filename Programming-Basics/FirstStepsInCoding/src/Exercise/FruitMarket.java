package Exercise;

import java.util.Scanner;

public class FruitMarket {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double berriesPrice = Double.parseDouble(scan.nextLine());
        double bananas = Double.parseDouble(scan.nextLine());
        double oranges = Double.parseDouble(scan.nextLine());
        double raspberries = Double.parseDouble(scan.nextLine());
        double berries = Double.parseDouble(scan.nextLine());

        double raspberriesPrice = berriesPrice / 2;
        double orangesPrice = raspberriesPrice - raspberriesPrice * 0.4;
        double bananasPrice = raspberriesPrice - raspberriesPrice * 0.8;

        double raspberriesSum = raspberries * raspberriesPrice;
        double orangesSume = oranges * orangesPrice;
        double bananasSume = bananas * bananasPrice;
        double berriesSum = berries * berriesPrice;
        double sum = berriesSum + raspberriesSum + bananasSume + orangesSume;

        System.out.printf("%.2f", sum);
    }
}
