package Exercise;

import java.util.Scanner;

public class HalfSumElement {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        int sum = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int currentNum = Integer.parseInt(scan.nextLine());

            sum = sum + currentNum;

            if (currentNum > max)
                max = currentNum;
        }
        int sumWithoutMax = sum - max;

        if (max == sumWithoutMax) {
            System.out.printf("Yes%nSum = %d", max);
        } else {
            System.out.printf("No%nDiff = %d", Math.abs(sumWithoutMax - max));
        }
    }
}
