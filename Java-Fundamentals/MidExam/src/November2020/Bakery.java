package November2020;

import java.util.Scanner;

public class Bakery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int biscuitsPerWorker = Integer.parseInt(scanner.nextLine());
        int workersCount = Integer.parseInt(scanner.nextLine());
        int otherFactoryProduction = Integer.parseInt(scanner.nextLine());

        int myFactoryProduction = 0;
        for (int i = 1; i <= 30; i++) {
            if (i % 3 == 0) {
                myFactoryProduction += (int) Math.floor(biscuitsPerWorker * workersCount * 0.75);
            } else {
                myFactoryProduction += biscuitsPerWorker * workersCount;
            }
        }

        System.out.printf("You have produced %d biscuits for the past month.%n", myFactoryProduction);

        int diff = Math.abs(otherFactoryProduction - myFactoryProduction);
        double percent = diff / (double) otherFactoryProduction * 100;

        if (otherFactoryProduction < myFactoryProduction) {
            System.out.printf("You produce %.2f percent more biscuits.", percent);
        } else {
            System.out.printf("You produce %.2f percent less biscuits.", percent);
        }
    }
}
