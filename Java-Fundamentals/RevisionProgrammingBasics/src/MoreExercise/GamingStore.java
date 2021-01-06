package MoreExercise;

import java.util.Scanner;

public class GamingStore {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double balance = Double.parseDouble(scan.nextLine());
        String gameName = scan.nextLine();
        double purchases = balance;

        while (!gameName.equals("Game Time")) {
            double price = 0;
            switch (gameName) {
                case "OutFall 4":
                    price = 39.99;
                    break;
                case "CS: OG":
                    price = 15.99;
                    break;
                case "Zplinter Zell":
                    price = 19.99;
                    break;
                case "Honored 2":
                    price = 59.99;
                    break;
                case "RoverWatch":
                    price = 29.99;
                    break;
                case "RoverWatch Origins Edition":
                    price = 39.99;
                    break;
                default:
                    System.out.println("Not Found");
            }
            if (price > purchases) {
                System.out.println("Too Expensive");
            } else if (price > 0) {
                purchases -= price;
                System.out.println("Bought " + gameName);
            }

            if (purchases == 0) {
                System.out.println("Out of money!");
                return;
            }

            gameName = scan.nextLine();
        }
        double spent = balance - purchases;
        System.out.printf("Total spent: $%.2f. Remaining: $%.2f", spent, balance - spent);
    }
}
