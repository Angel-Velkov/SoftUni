package April2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovingTarget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> targets = parseLineOfNumbers(scanner);
        String input;

        while (!"End".equals(input = scanner.nextLine())) {
            String[] inputAsArray = input.split(" ");
            String command = inputAsArray[0];
            int index = Integer.parseInt(inputAsArray[1]);

            switch (command) {
                case "Shoot":
                    int power = Integer.parseInt(inputAsArray[2]);

                    if (0 <= index && index < targets.size()) {
                        shootingTheTarget(targets, index, power);
                    }
                    break;
                case "Add":
                    int value = Integer.parseInt(inputAsArray[2]);

                    if (0 <= index && index < targets.size()) {
                        insertNewTarget(targets, index, value);
                    } else {
                        System.out.println("Invalid placement!");
                    }
                    break;
                case "Strike":
                    int radius = Integer.parseInt(inputAsArray[2]);
                    int leftBound = index - radius;
                    int rightBound = index + radius;
                    if (0 <= leftBound && rightBound < targets.size()) {
                        strikeTheTarget(targets, index, radius);
                    } else {
                        System.out.println("Strike missed!");
                    }
                    break;
            }
        }
        for (int i = 0; i < targets.size(); i++) {
            System.out.print(targets.get(i));
            if (i < targets.size() - 1) {
                System.out.print("|");
            }
        }
    }

    private static void strikeTheTarget(List<Integer> targets, int index, int radius) {
        int leftRange = index - radius;
        int rightRange = index + radius;

        for (int i = leftRange; i <= rightRange; i++) {
            targets.remove(leftRange);
        }
    }

    private static void insertNewTarget(List<Integer> targets, int index, int value) {
        targets.add(index, value);
    }

    private static void shootingTheTarget(List<Integer> targets, int index, int power) {
        int targetsHealth = targets.get(index) - power;
        targets.set(index, targetsHealth);
        if (targetsHealth <= 0) {
            targets.remove(index);
        }
    }

    private static List<Integer> parseLineOfNumbers(Scanner scanner) {
        List<Integer> numbers = new ArrayList<>();
        String[] numbersAsString = scanner.nextLine().split(" ");

        for (String s : numbersAsString) {
            int num = Integer.parseInt(s);
            numbers.add(num);
        }
        return numbers;
    }
}
