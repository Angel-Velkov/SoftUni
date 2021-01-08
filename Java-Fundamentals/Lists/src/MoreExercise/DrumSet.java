package MoreExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DrumSet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double savings = Double.parseDouble(scanner.nextLine());
        List<Integer> drumSetQuality = parseLineOfNumbers(scanner);
        List<Integer> drumSet = new ArrayList<>(drumSetQuality);

        String input;
        while (!"Hit it again, Gabsy!".equals(input = scanner.nextLine())) {
            int power = Integer.parseInt(input);
            int i = 0;
            while (i < drumSet.size()) {
                int currentQuality = drumSet.get(i) - power;

                if (currentQuality > 0) {
                    drumSet.set(i, currentQuality);
                    i++;
                } else {
                    int cost = drumSetQuality.get(i) * 3;

                    if (savings - cost < 0) {
                        drumSet.remove(i);
                        drumSetQuality.remove(i);
                    } else {
                        drumSet.set(i, drumSetQuality.get(i));
                        savings -= cost;
                        i++;
                    }
                }
            }
        }
        for (int drum : drumSet) {
            System.out.print(drum + " ");
        }
        System.out.println();
        System.out.printf("Gabsy has %.2flv.", savings);
    }

    private static List<Integer> parseLineOfNumbers(Scanner scanner) {
        List<Integer> numbers = new ArrayList<>();
        String[] numbersAsString = scanner.nextLine().split("\\s+");

        for (String s : numbersAsString) {
            int num = Integer.parseInt(s);
            numbers.add(num);
        }
        return numbers;
    }
}
