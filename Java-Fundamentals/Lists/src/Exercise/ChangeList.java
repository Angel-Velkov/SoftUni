package Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = parseLineOfNumbers(scanner);

        String input;

        while (!"end".equals(input = scanner.nextLine())) {
            String[] inputAsArray = input.split("\\s+");
            String command = inputAsArray[0];
            int element = Integer.parseInt(inputAsArray[1]);

            switch (command) {
                case "Delete":
                    int i = 0;
                    while (i < numbers.size()) {
                        if (numbers.get(i) == element) {
                            numbers.remove((Integer) element);
                        } else {
                            i++;
                        }
                    }
                    break;
                case "Insert":
                    int position = Integer.parseInt(inputAsArray[2]);
                    if (position < numbers.size()) {
                        numbers.add(position, element);
                    }
                    break;
            }
        }

        for (int num : numbers) {
            System.out.print(num + " ");
        }

    }

    private static List<Integer> parseLineOfNumbers(Scanner scanner) {
        String[] numbersAsString = scanner.nextLine().split(" ");
        List<Integer> numbers = new ArrayList<>();

        for (String s : numbersAsString) {
            int number = Integer.parseInt(s);
            numbers.add(number);
        }
        return numbers;
    }
}
