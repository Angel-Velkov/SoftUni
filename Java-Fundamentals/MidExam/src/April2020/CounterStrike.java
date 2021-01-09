package April2020;

import java.util.Scanner;

public class CounterStrike {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int energy = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();
        int battleCounter = 0;
        boolean wonTheBattle = true;
        while (!input.equals("End of battle")) {
            int distance = Integer.parseInt(input);
            if (energy - distance >= 0) {
                energy -= distance;
                battleCounter++;
                if (battleCounter % 3 == 0) {
                    energy += battleCounter;
                }
            } else {
                System.out.println("Not enough energy! Game ends with " + battleCounter + " won battles and " + energy + " energy");
                wonTheBattle = false;
                break;
            }
            input = scanner.nextLine();
        }

        if (wonTheBattle) {
            System.out.println("Won battles: " + battleCounter + ". Energy left: " + energy);
        }
    }
}
