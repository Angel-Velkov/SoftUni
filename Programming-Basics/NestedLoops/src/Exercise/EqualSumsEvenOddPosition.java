package Exercise;

import java.util.Scanner;

public class EqualSumsEvenOddPosition {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int start = Integer.parseInt(scan.nextLine());
        int end = Integer.parseInt(scan.nextLine());

        for (int i = start; i <= end; i++) {
            int evenSum = 0;
            int oddSum = 0;
            String charSymbols = i + "";
            for (int j = 0; j < charSymbols.length(); j++) {
                int currentNum = Integer.parseInt("" + charSymbols.charAt(j));
                if (j % 2 == 0) {
                    evenSum += currentNum;
                } else {
                    oddSum += currentNum;
                }
            }
            if (oddSum == evenSum) {
                System.out.print(i + " ");
            }
        }
    }
}
