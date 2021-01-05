package Exercise;

import java.util.Scanner;

public class BirthdayParty {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int hall = Integer.parseInt(scan.nextLine());

        int cake = hall / 5;
        double drinks = cake - cake * 0.45;
        int animator = hall / 3;

        double totalSum = hall + cake + drinks + animator;

        System.out.printf("%.1f", totalSum);
    }
}
