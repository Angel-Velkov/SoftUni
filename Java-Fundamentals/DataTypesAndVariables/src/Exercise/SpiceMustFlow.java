package Exercise;

import java.util.Scanner;

public class SpiceMustFlow {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int startingYield = Integer.parseInt(scan.nextLine());
        int daysCounter = 0;
        int totalYield = 0;

        while (startingYield >= 100) {
            totalYield += (startingYield - 26);
            startingYield -= 10;
            daysCounter++;
        }
        if (totalYield > 26) {
            totalYield -= 26;
        }

        System.out.println(daysCounter);
        System.out.println(totalYield);
    }
}
