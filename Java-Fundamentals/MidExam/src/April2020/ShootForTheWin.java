package April2020;

import java.util.Arrays;
import java.util.Scanner;

public class ShootForTheWin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] targets = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String input;
        int shotCounter = 0;
        while (!"End".equals(input = scanner.nextLine())) {
            int index = Integer.parseInt(input);

            if (0 <= index && index < targets.length) {
                int damage = targets[index];
                if (damage != -1) {
                    shotCounter++;
                    targets[index] = -1;
                    for (int i = 0; i < targets.length; i++) {
                        if (targets[i] != -1) {
                            if (targets[i] <= damage) {
                                targets[i] += damage;
                            } else {
                                targets[i] = Math.max(-1, targets[i] - damage);
                            }
                        }
                    }
                }
            }
        }
        System.out.print("Shot targets: " + shotCounter + " -> ");
        for (int target : targets) {
            System.out.print(target + " ");
        }
    }
}
