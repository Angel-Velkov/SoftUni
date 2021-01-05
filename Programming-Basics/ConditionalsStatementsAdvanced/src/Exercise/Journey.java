package Exercise;

import java.util.Scanner;

public class Journey {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double budget = Double.parseDouble(scan.nextLine());
        String season = scan.nextLine();
        double totalPrice = 0;
        String destination;
        String typeOfHoliday = "";

        if (budget <= 100) {
            destination = "Bulgaria";

            switch (season) {
                case "summer":
                    totalPrice = budget * 0.3;
                    typeOfHoliday = "Camp";
                    break;
                case "winter":
                    totalPrice = budget * 0.7;
                    typeOfHoliday = "Hotel";
                    break;
            }
        } else if (budget <= 1000) {
            destination = "Balkans";
            switch (season) {
                case "summer":
                    totalPrice = budget * 0.4;
                    typeOfHoliday = "Camp";
                    break;
                case "winter":
                    totalPrice = budget * 0.8;
                    typeOfHoliday = "Hotel";
                    break;
            }
        } else {
            destination = "Europe";
            switch (season) {
                case "summer":
                case "winter":
                    totalPrice = budget * 0.9;
                    typeOfHoliday = "Hotel";
            }
        }
        System.out.println("Somewhere in " + destination);
        System.out.printf("%s - %.2f", typeOfHoliday, totalPrice);
    }
}
