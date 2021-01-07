package MoreExercise;

import java.util.Scanner;

public class CenterPoint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int x1 = Integer.parseInt(scanner.nextLine());
        int y1 = Integer.parseInt(scanner.nextLine());
        int x2 = Integer.parseInt(scanner.nextLine());
        int y2 = Integer.parseInt(scanner.nextLine());

        printClosestCoordinatesToTheCenter(x1, y1, x2, y2);
    }

    private static void printClosestCoordinatesToTheCenter(int x1, int y1, int x2, int y2) {

        double firstSquaredDistance = Math.sqrt((x1 * x1) + (y1 * y1));
        double secondSquaredDistance = Math.sqrt((x2 * x2) + (y2 * y2));

        if (secondSquaredDistance < firstSquaredDistance) {
            System.out.printf("(%d, %d)", x2, y2);
        } else {
            System.out.printf("(%d, %d)", x1, y1);
        }
    }
}
