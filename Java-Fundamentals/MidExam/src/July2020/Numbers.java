package July2020;

import java.util.*;

public class Numbers {
    public static void main(String[] args) {
        int[] array = parseArray(new Scanner(System.in).nextLine());

        long sum = 0;
        for (int num : array) {
            sum += num;
        }
        double averageNum = sum / (double) array.length;
        List<Integer> aboveAverage = new ArrayList<>();
        for (int num : array) {
            if (num > averageNum) {
                aboveAverage.add(num);
            }
        }
        if (aboveAverage.isEmpty()) {
            System.out.println("No");
        } else {
            Collections.sort(aboveAverage);
            Collections.reverse(aboveAverage);

            for (int i = 0; i < Math.min(aboveAverage.size(), 5); i++) {
                System.out.print(aboveAverage.get(i) + " ");
            }
        }
    }

    private static int[] parseArray(String line) {
        String[] numbersAsString = line.split("\\s+");
        int[] numbers = new int[numbersAsString.length];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numbersAsString[i]);
        }
        return numbers;
    }

    //TODO: Методът е безполезнен, но попринцип можем да го вкараме в задачата
    private static void printArray(String separator, int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(separator);
            }
        }
    }
}
