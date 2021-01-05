package Exercise;

import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String type = scan.nextLine();
        int rows = Integer.parseInt(scan.nextLine());
        int columns = Integer.parseInt(scan.nextLine());
        double price = 0;

        switch (type) {
            case "Premiere":
                price = rows * columns * 12;
                break;
            case "Normal":
                price = rows * columns * 7.5;
                break;
            case "Discount":
                price = rows * columns * 5;
                break;
        }
        System.out.printf("%.2f leva", price);
    }
}
