import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static class Reservation {
        String holderName;
        int seats;

        Reservation(String holderName, int seats) {
            this.holderName = holderName;
            this.seats = seats;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Reservation> reservations = new ArrayList<>();

        String input;
        while (!"end".equals(input = scanner.nextLine())) {
            String[] namesAndSeats = input.split(" ");
            String name = namesAndSeats[0];
            int seats = Integer.parseInt(namesAndSeats[1]);

            Reservation r = new Reservation(name, seats);
            reservations.add(r);
        }
        int index = -1;
        String guest = scanner.nextLine();

        for (int i = 0; i < reservations.size(); i++) {
            if (guest.equals(reservations.get(i).holderName)) {
                index = i;
            }
        }

        if (index != -1) {
            System.out.println(reservations.get(index).seats);
        } else {
            System.out.printf("There's no %s in the list", guest);
        }
    }
}
