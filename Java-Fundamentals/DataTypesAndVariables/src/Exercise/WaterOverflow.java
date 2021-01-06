package Exercise;

import java.util.Scanner;

public class WaterOverflow {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        int waterTank = 0;

        for (int i = 0; i < n; i++) {
            int currentLitters = Integer.parseInt(scan.nextLine());

            if (currentLitters + waterTank > 255) {
                System.out.println("Insufficient capacity!");
            } else {
                waterTank += currentLitters;
            }
        }
        System.out.println(waterTank);
    }
}
