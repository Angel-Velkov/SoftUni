package Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardsGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> firstDeck = parseLineOfNumbers(scanner);
        List<Integer> secondDeck = parseLineOfNumbers(scanner);

        while (!firstDeck.isEmpty() && !secondDeck.isEmpty()) {
            if (firstDeck.get(0) > secondDeck.get(0)) {
                firstDeck.add(firstDeck.get(0));
                firstDeck.add(secondDeck.get(0));
                firstDeck.remove(0);
                secondDeck.remove(0);
            } else if (firstDeck.get(0) < secondDeck.get(0)) {
                secondDeck.add(secondDeck.get(0));
                secondDeck.add(firstDeck.get(0));
                secondDeck.remove(0);
                firstDeck.remove(0);
            } else if (firstDeck.get(0).equals(secondDeck.get(0))) {
                firstDeck.remove(0);
                secondDeck.remove(0);
            }
        }
        String winner;
        int sum = 0;
        if (firstDeck.isEmpty()) {
            winner = "Second";
            for (int n : secondDeck) {
                sum += n;
            }
        } else {
            winner = "First";
            for (int n : firstDeck) {
                sum += n;
            }
        }

        System.out.printf("%s player wins! Sum: %d", winner, sum);
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
