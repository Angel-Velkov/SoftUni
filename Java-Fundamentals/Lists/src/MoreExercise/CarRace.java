package MoreExercise;

import java.util.Arrays;
import java.util.Scanner;

public class CarRace {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        double firstRacer = 0;

        for (int i = 0; i < numbers.length / 2; i++) {
            if (numbers[i] == 0) {
                firstRacer *= 0.8;
            } else {
                firstRacer += numbers[i];
            }
        }

        double secondRacer = 0;
        for (int i = numbers.length - 1; i > numbers.length / 2; i--) {
            if (numbers[i] == 0) {
                secondRacer *= 0.8;
            } else {
                secondRacer += numbers[i];
            }
        }

        String winner;
        if (firstRacer < secondRacer) {
            winner = "left";
        } else {
            winner = "right";
        }

        System.out.printf("The winner is %s with total time: %.1f", winner, Math.min(firstRacer, secondRacer));
    }
}
