package Lab;

import java.util.Scanner;

public class YardGreening {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double greenArea = Double.parseDouble(scan.nextLine());
        double price = greenArea * 7.61;

        double discount = price * 0.18;
        double totalSum = price - discount;

        System.out.printf("The final price is: %.2f lv. %n", totalSum);
        System.out.printf("The discount is: %.2f lv.", discount);
    }
}
