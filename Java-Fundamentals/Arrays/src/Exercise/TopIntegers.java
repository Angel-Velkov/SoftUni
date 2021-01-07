package Exercise;

import java.util.Scanner;

public class TopIntegers {
    public static void main(String[] args) {

        String[] numbersAsString = new Scanner(System.in).nextLine().split(" ");
        int[] numbers = new int[numbersAsString.length];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numbersAsString[i]);
        }

        for (int i = 0; i < numbers.length - 1; i++) {
            int topInteger = numbers[i];
            boolean isBigger = true;
            for (int j = i + 1; j < numbers.length; j++) {
                if (topInteger <= numbers[j]) {
                    isBigger = false;
                    break;
                }
            }
            if (isBigger) {
                System.out.print(topInteger + " ");
            }
        }
        System.out.println(numbers[numbers.length - 1]);
    }
}
