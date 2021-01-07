package Lab;

import java.util.Scanner;

public class Orders {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String product = scan.nextLine();
        int quantity = scan.nextInt();

        calculateThePerice(product, quantity);
    }

    private static void calculateThePerice(String product, int quantity) {
        double bill = 0;
        switch (product) {
            case "coffee":
                bill = 1.5;
                break;
            case "water":
                bill = 1.0;
                break;
            case "coke":
                bill = 1.4;
                break;
            case "snacks":
                bill = 2.0;
                break;
        }
        bill *= quantity;
        System.out.printf("%.2f", bill);
    }
}
