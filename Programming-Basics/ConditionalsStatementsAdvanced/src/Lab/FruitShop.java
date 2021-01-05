package Lab;

import java.util.Scanner;

public class FruitShop {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String fruit = scan.nextLine();
        String day = scan.nextLine();
        double quantity = Double.parseDouble(scan.nextLine());

        double totalPrice = 0;

        if (day.equals("Monday") || day.equals("Tuesday") || day.equals("Wednesday") || day.equals("Thursday") || day.equals("Friday")) {
            switch (fruit) {
                case "banana":
                    totalPrice = quantity * 2.5;
                    break;
                case "apple":
                    totalPrice = quantity * 1.2;
                    break;
                case "orange":
                    totalPrice = quantity * 0.85;
                    break;
                case "grapefruit":
                    totalPrice = quantity * 1.45;
                    break;
                case "kiwi":
                    totalPrice = quantity * 2.7;
                    break;
                case "pineapple":
                    totalPrice = quantity * 5.5;
                    break;
                case "grapes":
                    totalPrice = quantity * 3.85;
                    break;
            }
        } else if (day.equals("Saturday") || day.equals("Sunday")) {
            switch (fruit) {
                case "banana":
                    totalPrice = quantity * 2.7;
                    break;
                case "apple":
                    totalPrice = quantity * 1.25;
                    break;
                case "orange":
                    totalPrice = quantity * 0.9;
                    break;
                case "grapefruit":
                    totalPrice = quantity * 1.6;
                    break;
                case "kiwi":
                    totalPrice = quantity * 3;
                    break;
                case "pineapple":
                    totalPrice = quantity * 5.6;
                    break;
                case "grapes":
                    totalPrice = quantity * 4.20;
                    break;
            }
        }
        if (totalPrice > 0) {
            System.out.printf("%.2f", totalPrice);
        } else {
            System.out.println("error");
        }
    }
}
