package Lab;

import java.util.Scanner;

public class GraduationPt2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String name = scan.nextLine();
        int counter = 1;
        int repeat = 0;
        double sum = 0;
        double averageGrade = 0;
        while (counter <= 12) {
            double grade = Double.parseDouble(scan.nextLine());
            if (grade < 4.00) {
                repeat++;
                if (repeat > 1) {
                    System.out.printf("%s has been excluded at %d grade", name, counter);
                    return;
                }
                continue;
            }
            counter++;
            sum += grade;
        }
        averageGrade = sum / 12;
        System.out.printf("%s graduated. Average grade: %.2f", name, averageGrade);
    }
}
