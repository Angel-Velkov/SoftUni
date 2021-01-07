package Exercise;

import java.util.Scanner;

public class CommonElements {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] firstInput = scan.nextLine().split(" ");
        String[] secondString = scan.nextLine().split(" ");

        for (String second : secondString) {
            for (String first : firstInput) {
                if (first.equals(second)) {
                    System.out.print(first + " ");
                }
            }
        }
    }
}
