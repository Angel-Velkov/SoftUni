package Lab;

import java.util.Scanner;

public class SmallShop {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String product = scan.nextLine();
        String city = scan.nextLine();
        double quantity = Double.parseDouble(scan.nextLine());
        double totalPrice = 0;
        if (product.equals("coffee")) {
            switch (city) {
                case "Sofia":
                    totalPrice = quantity * 0.5;
                    break;
                case "Plovdiv":
                    totalPrice = quantity * 0.4;
                    break;
                case "Varna":
                    totalPrice = quantity * 0.45;
                    break;
            }
        } else if (product.equals("water")) {
            switch (city) {
                case "Sofia":
                    totalPrice = quantity * 0.8;
                    break;
                case "Plovdiv":
                    totalPrice = quantity * 0.7;
                    break;
                case "Varna":
                    totalPrice = quantity * 0.7;
                    break;
            }
        } else if (product.equals("beer")) {
            switch (city) {
                case "Sofia":
                    totalPrice = quantity * 1.20;
                    break;
                case "Plovdiv":
                    totalPrice = quantity * 1.15;
                    break;
                case "Varna":
                    totalPrice = quantity * 1.10;
                    break;
            }
        } else if (product.equals("sweets")) {
            switch (city) {
                case "Sofia":
                    totalPrice = quantity * 1.45;
                    break;
                case "Plovdiv":
                    totalPrice = quantity * 1.3;
                    break;
                case "Varna":
                    totalPrice = quantity * 1.35;
                    break;
            }
        } else if (product.equals("peanuts")) {
            switch (city) {
                case "Sofia":
                    totalPrice = quantity * 1.6;
                    break;
                case "Plovdiv":
                    totalPrice = quantity * 1.5;
                    break;
                case "Varna":
                    totalPrice = quantity * 1.55;
                    break;
            }
        }
        System.out.println(totalPrice);
    }
}
