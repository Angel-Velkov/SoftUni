package Exercise;

import java.util.Scanner;

public class PokeMon {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int power = Integer.parseInt(scan.nextLine());
        int distance = Integer.parseInt(scan.nextLine());
        int exhaustionFactor = Integer.parseInt(scan.nextLine());

        int success = 0;
        double percentOfPower = 0.50 * power;

        while (power >= distance) {
            power -= distance;
            success++;

            if (percentOfPower == power && exhaustionFactor != 0) {
                power /= exhaustionFactor;
            }
        }
        System.out.println(power);
        System.out.println(success);
    }
}
