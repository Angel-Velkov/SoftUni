package Lab;

import java.util.Scanner;

public class PetShop {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int dogs = Integer.parseInt(scan.nextLine());
        int animals = Integer.parseInt(scan.nextLine());

        double food = dogs * 2.5 + animals * 4;

        System.out.println(food + " lv.");
    }
}
