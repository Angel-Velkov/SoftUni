package Lab;

import java.util.Scanner;

public class CinemaTickets {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String movie = scan.nextLine();
        double student = 0.0;
        double standard = 0.0;
        double kid = 0.0;
        int allTickets = 0;

        while (!movie.equals("Finish")) {
            double freeSeats = Double.parseDouble(scan.nextLine());
            int ticketsPerMovie = 0;

            for (int i = 1; i <= freeSeats; i++) {
                String type = scan.nextLine();
                switch (type) {
                    case "student":
                        student++;
                        break;
                    case "standard":
                        standard++;
                        break;
                    case "kid":
                        kid++;
                        break;
                }
                if (type.equals("End")) {
                    break;
                }
                allTickets++;
                ticketsPerMovie++;
            }
            System.out.printf("%s - %.2f%% full.%n", movie, ticketsPerMovie / freeSeats * 100);

            movie = scan.nextLine();
        }
        System.out.printf("Total tickets: %d%n", allTickets);

        System.out.printf("%.2f%% student tickets.%n", student / allTickets * 100);
        System.out.printf("%.2f%% standard tickets.%n", standard / allTickets * 100);
        System.out.printf("%.2f%% kids tickets.", kid / allTickets * 100);
    }
}
