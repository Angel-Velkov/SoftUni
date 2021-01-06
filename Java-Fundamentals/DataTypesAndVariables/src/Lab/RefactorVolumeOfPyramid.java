package Lab;

import java.util.Scanner;

public class RefactorVolumeOfPyramid {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Length: ");
        double length = scan.nextDouble();
        System.out.print("Width: ");
        double width = scan.nextDouble();
        System.out.print("Height: ");
        double height = scan.nextDouble();
        double volume = (length * width * height) / 3;
        System.out.printf("Pyramid Volume: %.2f", volume);
    }
}
