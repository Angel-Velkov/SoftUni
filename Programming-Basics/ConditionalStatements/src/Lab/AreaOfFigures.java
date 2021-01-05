package Lab;

import java.util.Scanner;

public class AreaOfFigures {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String figure = scan.nextLine();

        if (figure.equals("square")) {
            double sideS = Double.parseDouble(scan.nextLine());

            double squareArea = sideS * sideS;

            System.out.printf("%.3f", squareArea);

        } else if (figure.equals("rectangle")) {
            double sideRA = Double.parseDouble(scan.nextLine());
            double sideRB = Double.parseDouble(scan.nextLine());

            double rectangleArea = sideRA * sideRB;

            System.out.printf("%.3f", rectangleArea);
        } else if (figure.equals("circle")) {
            double radius = Double.parseDouble(scan.nextLine());

            double circleArea = radius * radius * Math.PI;

            System.out.printf("%.3f", circleArea);
        } else if (figure.equals("triangle")) {
            double hypotenuse = Double.parseDouble(scan.nextLine());
            double height = Double.parseDouble(scan.nextLine());

            double triangleArea = hypotenuse * height / 2;

            System.out.printf("%.3f", triangleArea);
        }
    }
}
