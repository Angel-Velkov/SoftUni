package Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GaussTrick {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Integer> numbers = parseLineOfNumbers(scan);

        for (int i = 0; i < numbers.size() - 1; i++) {
            int firstNumber = numbers.get(i);
            int lastNumber = numbers.get(numbers.size() - 1);
            numbers.set(i, firstNumber + lastNumber);
            numbers.remove(numbers.size() - 1);
        }

        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }

    private static List<Integer> parseLineOfNumbers(Scanner scan) {
        List<Integer> numbers = new ArrayList<>();
        String line = scan.nextLine();
        String[] numbersAsString = line.split(" ");

        for (String s : numbersAsString) {
            int number = Integer.parseInt(s);
            numbers.add(number);
        }
        return numbers;
    }
}
