package Exercise;

import java.util.Scanner;

public class WorldSwimmingRecord {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double recordTime = Double.parseDouble(scan.nextLine());
        double distance = Double.parseDouble(scan.nextLine());
        double meterPerSec = Double.parseDouble(scan.nextLine());

        double swimmingTime = distance * meterPerSec;
        double resistance = (Math.floor(distance / 15)) * 12.5;
        double totalTime = swimmingTime + resistance;

        if (totalTime < recordTime) {
            System.out.printf("Yes, he succeeded! The new world record is %.2f seconds.", totalTime);
        } else {
            System.out.printf("No, he failed! He was %.2f seconds slower.", Math.abs(recordTime - totalTime));
        }
    }
}
