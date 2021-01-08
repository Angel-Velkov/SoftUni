package Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> wagons = parseLineOfNumbers(scanner);
        int MaxCapacity = Integer.parseInt(scanner.nextLine());

        String input;
        while (!"end".equals(input = scanner.nextLine())) {
            String[] inputAsArray = input.split(" ");
            if (inputAsArray[0].equals("Add")) {
                int passengersInWagon = Integer.parseInt(inputAsArray[1]);
                wagons.add(passengersInWagon);
            } else {
                int passengers = Integer.parseInt(inputAsArray[0]);
                for (int i = 0; i < wagons.size(); i++) {
                    int passengersToAdd = wagons.get(i) + passengers;
                    if (passengersToAdd <= MaxCapacity){
                        wagons.set(i,passengersToAdd);
                        break;
                    }
                }
            }

        }

        for (int number : wagons) {
            System.out.print(number + " ");
        }

    }

    private static List<Integer> parseLineOfNumbers(Scanner scanner) {
        String[] numbersAString = scanner.nextLine().split(" ");
        List<Integer> wagons = new ArrayList<>();

        for (String s : numbersAString) {
            int number = Integer.parseInt(s);
            wagons.add(number);
        }
        return wagons;
    }
}
