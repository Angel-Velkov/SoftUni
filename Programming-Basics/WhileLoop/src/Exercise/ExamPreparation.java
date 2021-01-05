package Exercise;

import java.util.Scanner;

public class ExamPreparation {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int lowGrade = Integer.parseInt(scan.nextLine());
        String input = scan.nextLine();

        int gradesCount = 0;
        int currentLowGrade = 0;
        String lastProblem = "";
        double averageGrade;
        int sum = 0;

        while (!input.equals("Enough")) {
            int grade = Integer.parseInt(scan.nextLine());
            lastProblem = input;
            gradesCount++;
            if (grade <= 4) {
                currentLowGrade++;
                if (currentLowGrade == lowGrade) {
                    System.out.printf("You need a break, %d poor grades.", currentLowGrade);
                    return;
                }
            }
            sum += grade;

            input = scan.nextLine();
        }
        averageGrade = sum * 1.0 / gradesCount;

        System.out.printf("Average score: %.2f%n", averageGrade);
        System.out.printf("Number of problems: %d%n", gradesCount);
        System.out.printf("Last problem: %s", lastProblem);
    }
}
