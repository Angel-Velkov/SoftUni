package Exercise;

import java.util.Scanner;

public class Volleyball {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String yearType = scanner.nextLine();
        int p = Integer.parseInt(scanner.nextLine());
        int h = Integer.parseInt(scanner.nextLine());
        double weekendsInSofia = (48 - h) * 3.0 / 4;
        double holidaysInSofia = p * 2.0 / 3;
        double result = h + weekendsInSofia + holidaysInSofia;

        if (yearType.equals("leap")) {
            result *= 1.15;
        }
        System.out.printf("%.0f", Math.floor(result));
    }
}
