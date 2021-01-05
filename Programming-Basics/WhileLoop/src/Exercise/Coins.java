package Exercise;

import java.util.Scanner;

public class Coins {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double money = Double.parseDouble(scan.nextLine());
        int counter = 0;
        int change = (int) (money * 100);

        counter = (change / 200);
        change = (change % 200);

        counter += (change / 100);
        change = (change % 100);

        counter += (change / 50);
        change = (change % 50);

        counter += (change / 20);
        change = (change % 20);

        counter += (change / 10);
        change = (change % 10);

        counter += (change / 5);
        change = (change % 5);

        counter += (change / 2);
        change = (change % 2);

        counter += change;

        System.out.println(counter);
    }
}
