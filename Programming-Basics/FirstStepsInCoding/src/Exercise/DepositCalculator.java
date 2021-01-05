package Exercise;

import java.util.Scanner;

public class DepositCalculator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double depositSum = Double.parseDouble(scan.nextLine());
        int depositTime = Integer.parseInt(scan.nextLine());
        double interestPerYear = Double.parseDouble(scan.nextLine());

        double interestPerMouth = depositSum * interestPerYear / 100 / 12;
        double sum = depositSum + depositTime * interestPerMouth;

        System.out.printf("%.2f", sum);
    }
}
