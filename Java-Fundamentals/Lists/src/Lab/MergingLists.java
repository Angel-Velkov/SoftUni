package Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MergingLists {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Integer> firstList = parseLineOfNumbers(scan);
        List<Integer> secondList = parseLineOfNumbers(scan);

        List<Integer> merge = new ArrayList<>();

        int firstIndex = 0;
        int secondIndex = 0;

        while (firstIndex < firstList.size() || secondIndex < secondList.size()) {
            if (firstIndex < firstList.size()) {
                merge.add(firstList.get(firstIndex));
            }

            if (secondIndex < secondList.size()) {
                merge.add(secondList.get(secondIndex));
            }

            firstIndex++;
            secondIndex++;
        }

        for (int number : merge) {
            System.out.print(number + " ");
        }
    }

    private static List<Integer> parseLineOfNumbers(Scanner scan) {
        String line = scan.nextLine();
        String[] numbersAsString = line.split(" ");
        List<Integer> numbers = new ArrayList<>();

        for (String s : numbersAsString) {
            int number = Integer.parseInt(s);
            numbers.add(number);
        }
        return numbers;
    }
}
