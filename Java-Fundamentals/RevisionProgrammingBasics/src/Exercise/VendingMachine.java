package Exercise;

import java.util.Scanner;

public class VendingMachine {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();
        double sum = 0;

        while (!input.equals("Start")) {
            double coins = Double.parseDouble(input);

            if (coins == 2 || coins == 1 || coins == 0.5 || coins == 0.2 || coins == 0.1) {
                sum += coins;
            } else {
                System.out.printf("Cannot accept %.2f%n", coins);
            }

            input = scan.nextLine();
        }

        String snacks = scan.nextLine();

        while (!snacks.equals("End")) {
            double price = 0;

            switch (snacks) {
                case "Nuts":
                    price = 2.0;
                    break;
                case "Water":
                    price = 0.7;
                    break;
                case "Crisps":
                    price = 1.5;
                    break;
                case "Soda":
                    price = 0.8;
                    break;
                case "Coke":
                    price = 1.0;
                    break;
                default:
                    System.out.println("Invalid product");
                    break;
            }
            if (sum < price) {
                System.out.println("Sorry, not enough money");
            } else if (price > 0) {
                System.out.println("Purchased " + snacks);
                sum -= price;
            }

            snacks = scan.nextLine();
        }
        System.out.printf("Change: %.2f", sum);
    }
}
