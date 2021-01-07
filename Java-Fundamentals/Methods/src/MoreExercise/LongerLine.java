package MoreExercise;

import java.util.Scanner;

public class LongerLine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int x1 = Integer.parseInt(scanner.nextLine());
        int y1 = Integer.parseInt(scanner.nextLine());
        int x2 = Integer.parseInt(scanner.nextLine());
        int y2 = Integer.parseInt(scanner.nextLine());
        int x3 = Integer.parseInt(scanner.nextLine());
        int y3 = Integer.parseInt(scanner.nextLine());
        int x4 = Integer.parseInt(scanner.nextLine());
        int y4 = Integer.parseInt(scanner.nextLine());

        double firstLine = calculateLengthFromTwoCoordinates(x1, y1, x2, y2);
        double secondLine = calculateLengthFromTwoCoordinates(x3, y3, x4, y4);

        if (firstLine >= secondLine) {
            printFarthestCoordinatesToTheCenter(x1, y1, x2, y2);
        } else {
            printFarthestCoordinatesToTheCenter(x3, y3, x4, y4);
        }
    }

    private static double calculateLengthFromTwoCoordinates(int x1, int y1, int x2, int y2) {
        double hypotenuse = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

        return hypotenuse;
    }

    private static void printFarthestCoordinatesToTheCenter(int x1, int y1, int x2, int y2) {

        double firstSquaredDistance = Math.sqrt((x1 * x1) + (y1 * y1));
        double secondSquaredDistance = Math.sqrt((x2 * x2) + (y2 * y2));

        if (secondSquaredDistance < firstSquaredDistance) {
            System.out.printf("(%d, %d)(%d, %d)", x2, y2, x1, y1);
        } else {
            System.out.printf("(%d, %d)(%d, %d)", x1, y1, x2, y2);
        }
    }
}
