package Exercise;

import java.util.Scanner;

public class HotelRoom {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String month = scan.nextLine();
        int nights = Integer.parseInt(scan.nextLine());
        double studio = 0;
        double apartment = 0;

        switch (month) {
            case "May":
            case "October":
                studio = 50 * nights;
                if (nights > 7 && nights < 14) {
                    studio *= 0.95;
                } else if (nights > 14) {
                    studio *= 0.7;
                }
                apartment = 65 * nights;
                break;
            case "June":
            case "September":
                studio = 75.2 * nights;
                if (nights > 14) {
                    studio *= 0.8;
                }
                apartment = 68.70 * nights;
                break;
            case "July":
            case "August":
                studio = 76 * nights;
                apartment = 77 * nights;
                break;
        }
        if (nights > 14) {
            apartment *= 0.9;
        }
        System.out.printf("Apartment: %.2f lv.%n", apartment);
        System.out.printf("Studio: %.2f lv.", studio);
    }
}
