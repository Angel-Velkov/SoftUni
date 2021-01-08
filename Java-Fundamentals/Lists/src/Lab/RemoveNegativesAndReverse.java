package Lab;

import java.util.*;

public class RemoveNegativesAndReverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = parseLineOfNumbers(scanner);

        int i = 0;
        while (i < numbers.size()) {
            int currentNum = numbers.get(i);
            if (currentNum < 0) {
                numbers.remove(i);
            } else {
                i++;
            }
        }

        if (numbers.isEmpty()) {
            System.out.println("empty");
        } else {
            Collections.reverse(numbers);

            for (int num : numbers) {
                System.out.print(num + " ");
            }
        }
    }

    private static List<Integer> parseLineOfNumbers(Scanner scanner) {
        String line = scanner.nextLine();
        String[] numbersAsString = line.split(" ");
        List<Integer> numbers = new ArrayList<>();

        for (String s : numbersAsString) {
            int num = Integer.parseInt(s);
            numbers.add(num);
        }
        return numbers;
    }
}
