package Lab;

import java.util.Scanner;

public class EqualArrays {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String firstArrayString = scan.nextLine();
        String[] firstStringNumbers = firstArrayString.split(" ");
        int[] firstNumbers = new int[firstStringNumbers.length];

        for (int i = 0; i < firstNumbers.length; i++) {
            firstNumbers[i] = Integer.parseInt(firstStringNumbers[i]);
        }

        String secondArrayString = scan.nextLine();
        String[] secondStringNumbers = secondArrayString.split(" ");
        int[] secondNumbers = new int[secondStringNumbers.length];

        for (int i = 0; i < secondNumbers.length; i++) {
            secondNumbers[i] = Integer.parseInt(secondStringNumbers[i]);
        }
        int index = -1;
        int sum = 0;
        for (int i = 0; i < firstStringNumbers.length; i++) {
            if (firstNumbers[i] != secondNumbers[i]) {
                index = i;
                break;
            } else {
                sum += firstNumbers[i];
            }
        }
        if (index == -1) {
            System.out.println("Arrays are identical. Sum: " + sum);
        } else {
            System.out.println("Arrays are not identical. Found difference at " + index + " index.");
        }
    }
}
