package EntranceAugust2020;

import java.util.Scanner;

public class TravelAgency {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int ticketsCount = Integer.parseInt(scan.nextLine());
        int budget = Integer.parseInt(scan.nextLine());
        int ticketPrice = Integer.parseInt(scan.nextLine());
        int totalPrice = ticketPrice * ticketsCount;
        int change = Math.abs(budget - totalPrice);

        if (budget < totalPrice) {
            System.out.printf("The budget of %d$ is not enough. Your client can't buy %d tickets with this budget!", budget, ticketsCount);
        } else {
            System.out.printf("You can sell your client %d tickets for the price of %d$!%n", ticketsCount, totalPrice);
            System.out.printf("Your client should become a change of %d$!", change);
        }
    }
}
