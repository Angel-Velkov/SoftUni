package Lab;

import java.util.Scanner;

public class ProjectsCreation {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String architect = scan.nextLine();
        int projects = Integer.parseInt(scan.nextLine());
        int totalTime = projects * 3;

        System.out.printf("The architect %s will need %d hours to complete %d project/s.", architect, totalTime, projects);
    }
}
