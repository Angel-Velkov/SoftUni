package Exercise;

import java.util.Scanner;

public class MaxSequenceOfEqualElements {
    public static void main(String[] args) {

        String[] numbers = new Scanner(System.in).nextLine().split(" ");

        int bestLength = 0;
        int currentLength = 1;
        String equalElement = "";
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i].equals(numbers[i + 1])) {
                currentLength++;
                if (currentLength > bestLength) {
                    bestLength = currentLength;
                    equalElement = numbers[i];
                }
            } else {
                currentLength = 1;
            }
        }
        for (int i = 0; i < bestLength; i++) {
            System.out.print(equalElement + " ");
        }
    }
}
