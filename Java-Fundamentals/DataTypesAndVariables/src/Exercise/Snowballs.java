package Exercise;

import java.util.Scanner;

public class Snowballs {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        long highestValue = 0;
        int bestSnow = 0;
        int bestTime = 0;
        int bestQuality = 0;

        for (int i = 0; i < n; i++) {
            int snowballSnow = Integer.parseInt(scan.nextLine());
            int snowballTime = Integer.parseInt(scan.nextLine());
            int snowballQuality = Integer.parseInt(scan.nextLine());

            long snowballValue = (long) Math.pow(snowballSnow / snowballTime, snowballQuality);
            if (snowballValue > highestValue) {
                highestValue = snowballValue;
                bestSnow = snowballSnow;
                bestTime = snowballTime;
                bestQuality = snowballQuality;
            }
        }
        System.out.printf("%d : %d = %d (%d)", bestSnow, bestTime, highestValue, bestQuality);
    }
}
