package MoreExercise;

import java.util.Scanner;

public class MultiplicationSign {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = Integer.parseInt(scanner.nextLine());
        int b = Integer.parseInt(scanner.nextLine());
        int c = Integer.parseInt(scanner.nextLine());

        int[] numbers = {a, b, c};

        findingTheTypeOfTheNumbersIfWeMultipleThem(numbers);
    }

    private static void findingTheTypeOfTheNumbersIfWeMultipleThem(int[] numbers) {
        int minusCounter = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                System.out.println("zero");
                return;
            }
            if (numbers[i] < 0) {
                minusCounter++;
            }
        }
        if (minusCounter % 2 == 0) {
            System.out.println("positive");
        } else {
            System.out.println("negative");
        }
    }
}
