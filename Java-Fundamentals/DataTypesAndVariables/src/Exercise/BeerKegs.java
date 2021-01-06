package Exercise;

import java.util.Scanner;

public class BeerKegs {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        double biggestKeg = 0;
        String kegName = "";

        for (int i = 0; i < n; i++) {
            String model = scan.nextLine();
            double radius = Double.parseDouble(scan.nextLine());
            int height = Integer.parseInt(scan.nextLine());

            double kegValue = Math.PI * radius * radius * height;

            if (kegValue > biggestKeg) {
                biggestKeg = kegValue;
                kegName = model;
            }
        }
        System.out.println(kegName);
    }
}
