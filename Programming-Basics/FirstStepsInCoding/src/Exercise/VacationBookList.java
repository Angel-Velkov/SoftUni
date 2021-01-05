package Exercise;

import java.util.Scanner;

public class VacationBookList {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int pagesPerBook = Integer.parseInt(scan.nextLine());
        int pagesPerHour = Integer.parseInt(scan.nextLine());
        int daysPerBook = Integer.parseInt(scan.nextLine());

        int bookHours = pagesPerBook / pagesPerHour;
        int wholeBook = bookHours / daysPerBook;

        System.out.println(wholeBook);
    }
}
