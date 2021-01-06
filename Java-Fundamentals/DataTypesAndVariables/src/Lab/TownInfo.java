package Lab;

import java.util.Scanner;

public class TownInfo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String townName = scan.nextLine();
        int population = scan.nextInt();
        int area = scan.nextInt();

        System.out.printf("Town %s has population of %d and area %d square km.", townName, population, area);
    }
}
