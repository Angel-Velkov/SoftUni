import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Integer> firstList = parseLineOfNumbers(scan);
        List<Integer> secondList = parseLineOfNumbers(scan);

        List<Integer> mergedList = new ArrayList<>();

        int firstIndex = 0;
        int secondIndex = 0;

        while (firstIndex < firstList.size() && secondIndex < secondList.size()) {
            int firstListNum = firstList.get(firstIndex);
            int secondListNum = secondList.get(secondIndex);

            if (firstListNum < secondListNum) {
                mergedList.add(firstListNum);
                firstIndex++;
            } else {
                mergedList.add(secondListNum);
                secondIndex++;
            }
        }

        while (firstIndex < firstList.size()) {
            mergedList.add(firstList.get(firstIndex++));
        }

        while (secondIndex < secondList.size()) {
            mergedList.add(secondList.get(secondIndex++));
        }

        for (int n : mergedList) {
            System.out.print(n + " ");
        }
    }

    private static List<Integer> parseLineOfNumbers(Scanner scan) {
        List<Integer> allNumbers = new ArrayList<>();
        String line = scan.nextLine();
        String[] numbersAsString = line.split(" ");

        for (String s : numbersAsString) {
            int number = Integer.parseInt(s);
            allNumbers.add(number);
        }
        return allNumbers;
    }
}
