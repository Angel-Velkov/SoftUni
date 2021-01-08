package Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = parseLineOfNumbers(scanner);
        int bombNum = scanner.nextInt();
        int bombPower = scanner.nextInt();

        while (numbers.contains(bombNum)) {
            int bombPosition = numbers.indexOf(bombNum);
            int leftBound = Math.max(0, bombPosition - bombPower);
            int rightBound = Math.min(numbers.size() - 1, bombPosition + bombPower);
            int explosion = Math.abs(leftBound - rightBound);

            for (int i = 0; i <= explosion; i++) {
                numbers.remove(leftBound);
            }
        }

        int sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        System.out.println(sum);
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
