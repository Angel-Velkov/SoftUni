package July2020;

import java.util.Scanner;

public class ArrayModifier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = parseArray(scanner.nextLine());

        String input;

        while (!"end".equals(input = scanner.nextLine())) {
            String[] inputAsArr = input.split("\\s+");
            String command = inputAsArr[0];

            switch (command) {
                case "swap" -> swapTwoNumbers(numbers, Integer.parseInt(inputAsArr[1]), Integer.parseInt(inputAsArr[2]));
                case "multiply" -> multiplyTwoNumbers(numbers, Integer.parseInt(inputAsArr[1]), Integer.parseInt(inputAsArr[2]));
                case "decrease" -> decreasesEveryNumByOne(numbers);
            }
        }
        printArray(", ", numbers);
    }

    private static int[] parseArray(String line) {
        String[] numbersAsString = line.split("\\s+");
        int[] numbers = new int[numbersAsString.length];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numbersAsString[i]);
        }
        return numbers;
    }

    private static void printArray(String separator, int[] arrray) {
        for (int i = 0; i < arrray.length; i++) {
            System.out.print(arrray[i]);
            if (i < arrray.length - 1) {
                System.out.print(separator);
            }
        }
    }

    private static void decreasesEveryNumByOne(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers[i] - 1;
        }
    }

    private static void multiplyTwoNumbers(int[] numbers, int firstIndex, int secondIndex) {
        numbers[firstIndex] = numbers[firstIndex] * numbers[secondIndex];
    }

    private static void swapTwoNumbers(int[] numbers, int firstIndex, int secondIndex) {
        int oldFirstNum = numbers[firstIndex];
        numbers[firstIndex] = numbers[secondIndex];
        numbers[secondIndex] = oldFirstNum;
    }
}
