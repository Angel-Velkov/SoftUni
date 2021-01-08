package Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = parseListOfNumbers(scanner);
        String input;

        while (!"End".equals(input = scanner.nextLine())) {
            String[] inputAsArray = input.split(" ");
            String command = inputAsArray[0];
            switch (command) {
                case "Add":
                    int numberToAdd = Integer.parseInt(inputAsArray[1]);
                    numbers.add(numberToAdd);
                    break;
                case "Insert":
                    int numberToInsert = Integer.parseInt(inputAsArray[1]);
                    int index = Integer.parseInt(inputAsArray[2]);
                    if (0 <= index && index < numbers.size()) {
                        numbers.add(index, numberToInsert);
                    } else {
                        System.out.println("Invalid index");
                    }
                    break;
                case "Remove":
                    int indexToRemove = Integer.parseInt(inputAsArray[1]);
                    if (0 <= indexToRemove && indexToRemove < numbers.size()) {
                        numbers.remove(indexToRemove);
                    } else {
                        System.out.println("Invalid index");
                    }
                    break;
                case "Shift":
                    String direction = inputAsArray[1];
                    int count = Integer.parseInt(inputAsArray[2]);

                    if (direction.equals("left")) {
                        putsTheFirstNumAtTheEnd(numbers, count);

                    } else if (direction.equals("right")) {
                        putsTheLastNumAtTheBeginning(numbers, count);
                    }
                    break;
            }
        }
        for (int num : numbers) {
            System.out.print(num + " ");
        }
    }

    private static List<Integer> parseListOfNumbers(Scanner scanner) {
        String[] numbersAsString = scanner.nextLine().split(" ");
        List<Integer> numbers = new ArrayList<>();

        for (String s : numbersAsString) {
            int num = Integer.parseInt(s);
            numbers.add(num);
        }
        return numbers;
    }

    private static void putsTheFirstNumAtTheEnd(List<Integer> numbers, int count) {
        for (int i = 0; i < count; i++) {
            numbers.add(numbers.remove(0));
        }
    }

    private static void putsTheLastNumAtTheBeginning(List<Integer> numbers, int count) {
        for (int i = 0; i < count; i++) {
            numbers.add(0, numbers.get(numbers.size() - 1));
            numbers.remove(numbers.size() - 1);
        }
    }
}
