package Exercise;

import java.util.ArrayDeque;
import java.util.Scanner;

public class PoisonousPlants3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[] plants = new int[n];
        for (int i = 0; i < n; i++) {
            plants[i] = scanner.nextInt();
        }

        ArrayDeque<Integer> indexes = new ArrayDeque<>();
        indexes.push(0);

        int[] days = new int[n];
        int maxSpan = 0;
        for (int i = 1; i < n; i++) {
            int maxDays = 0;
            while (indexes.size() > 0 && plants[indexes.peek()] >= plants[i]) {
                maxDays = Math.max(maxDays, days[indexes.pop()]);
            }

            if (indexes.size() > 0) {
                days[i] = maxDays + 1;
                if (days[i] > maxSpan) {
                    maxSpan = days[i];
                }
            }

            indexes.push(i);
        }
        System.out.println(maxSpan);
    }
}
