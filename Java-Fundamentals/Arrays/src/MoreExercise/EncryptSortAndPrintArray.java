package MoreExercise;

import java.util.Arrays;
import java.util.Scanner;

public class EncryptSortAndPrintArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[] encryptMassage = new int[n];
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            int sum = 0;
            for (int j = 0; j < line.length(); j++) {
                char currentChar = line.charAt(j);
                switch (currentChar) {
                    case 'a':
                    case 'A':
                    case 'e':
                    case 'E':
                    case 'i':
                    case 'I':
                    case 'o':
                    case 'O':
                    case 'u':
                    case 'U':
                        sum += currentChar * line.length();
                        break;
                    default:
                        sum += currentChar / line.length();
                }
            }
            encryptMassage[i] = sum;
        }
        Arrays.sort(encryptMassage);
        for (int sum : encryptMassage) {
            System.out.println(sum);
        }
    }
}
