package Lab;

import java.util.Scanner;

public class EvenNumber {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            int num = Integer.parseInt(scan.nextLine());

            if (num % 2 != 0) {
                System.out.println("Please write an even number.");
            } else {
                System.out.println("The number is: " + num);
                break;
            }
        }
    }
}
