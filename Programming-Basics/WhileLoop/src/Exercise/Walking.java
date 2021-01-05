package Exercise;

import java.util.Scanner;

public class Walking {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int steps = 0;
        int targetSteps = 10000;
        while (steps < targetSteps) {
            String input = scan.nextLine();
            if (input.equals("Going home")) {
                int moreSteps = Integer.parseInt(scan.nextLine());
                steps += moreSteps;
                break;
            }
            steps += Integer.parseInt(input);
        }
        if (steps >= targetSteps) {
            System.out.println("Goal reached! Good job!");
            System.out.printf("%d steps over the goal!", steps - targetSteps);
        } else {
            System.out.printf("%d more steps to reach goal.", targetSteps - steps);
        }
    }
}
