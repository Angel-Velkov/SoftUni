package Exercise;

import java.util.Scanner;

public class OldBooks {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String wanted = scan.nextLine();
        String books = scan.nextLine();
        int counter = 0;

        while (!books.equals("No More Books")) {
            if (books.equals(wanted)) {
                System.out.printf("You checked %d books and found it.", counter);
                return;
            }
            counter++;
            books = scan.nextLine();
        }
        System.out.printf("The book you search is not here!%nYou checked %d books.", counter);
    }
}
