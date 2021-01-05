package Exercise;

import java.util.Scanner;

public class TrainTheTrainers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int peopleCount = Integer.parseInt(scan.nextLine());
        String presentation = scan.nextLine();
        double allGradesSum = 0;
        int counter = 0;

        while (!presentation.equals("Finish")) {
            double gradesSum = 0;

            for (int i = 1; i <= peopleCount; i++) {
                double currentGrade = Double.parseDouble(scan.nextLine());
                gradesSum += currentGrade;
                counter++;
            }
            System.out.printf("%s - %.2f.%n", presentation, gradesSum / peopleCount);
            allGradesSum += gradesSum;

            presentation = scan.nextLine();
        }
        System.out.printf("Student's final assessment is %.2f.", allGradesSum / counter);
    }
}
