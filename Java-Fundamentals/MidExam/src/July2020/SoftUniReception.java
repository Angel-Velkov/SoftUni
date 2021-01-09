package July2020;

import java.util.Scanner;

public class SoftUniReception {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int capacityPerHour = scanner.nextInt() + scanner.nextInt() + scanner.nextInt();
        int studentsCount = scanner.nextInt();

        int time = 0;
        while (studentsCount > 0) {
            time++;
            if (time % 4 != 0) {
                studentsCount -= capacityPerHour;
            }
        }
        System.out.printf("Time needed: %dh.", time);
    }
}
