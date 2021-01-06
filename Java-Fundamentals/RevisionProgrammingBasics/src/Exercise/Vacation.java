package Exercise;

import java.util.Scanner;

public class Vacation {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //               Friday        Saturday        Sunday
        //Students        8.45           9.80           10.46
        //Business       10.90          15.60           16
        //Regular        15             20              22.50


        int group = Integer.parseInt(scan.nextLine());
        String type = scan.nextLine();
        String day = scan.nextLine();
        double price;
        double totalPrice = 0;

        switch (type) {
            case "Students":
                if (day.equals("Friday")) {
                    price = 8.45;
                } else if (day.equals("Saturday")) {
                    price = 9.80;
                } else {
                    price = 10.46;
                }
                totalPrice = price * group;

                if (group >= 30) {
                    totalPrice *= 0.85;
                }
                break;
            case "Business":
                if (day.equals("Friday")) {
                    price = 10.90;
                } else if (day.equals("Saturday")) {
                    price = 15.60;
                } else {
                    price = 16;
                }
                totalPrice = price * group;
                if (group >= 100) {
                    totalPrice -= price * 10;
                }
                break;
            case "Regular":
                if (day.equals("Friday")) {
                    price = 15;
                } else if (day.equals("Saturday")) {
                    price = 20;
                } else {
                    price = 22.50;
                }
                totalPrice = price * group;
                if (10 <= group && group <= 20)
                    totalPrice *= 0.95;
                break;
        }
        System.out.printf("Total price: %.2f", totalPrice);
    }
}
