package Lab;

import java.util.Scanner;

public class TheatrePromotion {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String dayType = scan.nextLine();
        int age = Integer.parseInt(scan.nextLine());
        int ticketPrice = 0;

        if (0 <= age && age <= 18) {

            switch (dayType) {
                case "Weekday":
                    ticketPrice = 12;
                    break;
                case "Weekend":
                    ticketPrice = 15;
                    break;
                case "Holiday":
                    ticketPrice = 5;
                    break;
            }
        } else if (18 < age && age <= 64) {

            switch (dayType) {
                case "Weekday":
                    ticketPrice = 18;
                    break;
                case "Weekend":
                    ticketPrice = 20;
                    break;
                case "Holiday":
                    ticketPrice = 12;
                    break;
            }
        } else if (64 < age && age <= 122) {

            switch (dayType) {
                case "Weekday":
                    ticketPrice = 12;
                    break;
                case "Weekend":
                    ticketPrice = 15;
                    break;
                case "Holiday":
                    ticketPrice = 10;
                    break;
            }
        } else {
            System.out.print("Error!");
            return;
        }
        System.out.printf("%d$", ticketPrice);
    }
}
