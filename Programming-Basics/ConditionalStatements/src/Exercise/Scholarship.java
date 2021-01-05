package Exercise;

import java.util.Scanner;

public class Scholarship {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double income = Double.parseDouble(scanner.nextLine());
        double gradePointAverage = Double.parseDouble(scanner.nextLine());
        double minimumSalary = Double.parseDouble(scanner.nextLine());

        double socialScholarship = 0;
        if (income < minimumSalary && gradePointAverage > 4.5) {
            socialScholarship = minimumSalary * 0.35;
        }
        double excellentMarksScholarship = 0;
        if (gradePointAverage >= 5.5) {
            excellentMarksScholarship = 25 * gradePointAverage;
        }

        if (socialScholarship == 0 && excellentMarksScholarship == 0) {
            System.out.println("You cannot get a scholarship!");
        } else if (socialScholarship > excellentMarksScholarship) {
            System.out.printf("You get a Social scholarship %.0f BGN", Math.floor(socialScholarship));
        } else {
            System.out.printf("You get a scholarship for excellent results %.0f BGN", Math.floor(excellentMarksScholarship));
        }
    }
}
