package Lab;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SumAdjacentEqualNumbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Double> numbers = parseLineOfRealNumbers(scan);

        boolean search = true;
        while (search) {
            search = false;
            for (int i = 0; i < numbers.size() - 1; i++) {
                double currentNum = numbers.get(i);
                double nextNum = numbers.get(i + 1);
                if (currentNum == nextNum) {
                    numbers.set(i, currentNum + nextNum);
                    numbers.remove(i + 1);
                    search = true;
                    break;
                }
            }
        }

        for (double number : numbers) {
            DecimalFormat df = new DecimalFormat("#.#");
            System.out.print(df.format(number) + " ");
        }
    }

    private static List<Double> parseLineOfRealNumbers(Scanner scan) {
        List<Double> numbers = new ArrayList<>();
        String[] numbersAsString = scan.nextLine().split(" ");

        for (String s : numbersAsString) {
            double number = Double.parseDouble(s);
            numbers.add(number);
        }
        return numbers;
    }
}
