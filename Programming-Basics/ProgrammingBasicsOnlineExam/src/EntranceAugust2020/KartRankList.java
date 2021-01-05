package EntranceAugust2020;

import java.util.Scanner;

public class KartRankList {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int gold = 0;
        int silver = 0;
        int bronze = 0;
        int minTime = Integer.MAX_VALUE;
        String winnersName = "";
        int bestMin = 0;
        int bestSec = 0;

        while (true) {
            String nickname = scan.nextLine();
            if (nickname.equals("Finish")) {
                break;
            }
            int min = Integer.parseInt(scan.nextLine());
            int sec = Integer.parseInt(scan.nextLine());

            int time = min * 60 + sec;

            if (time < 55) {
                gold++;
            } else if (time <= 85) {
                silver++;
            } else if (time <= 120) {
                bronze++;
            }
            if (time < minTime) {
                minTime = time;
                winnersName = nickname;
                bestMin = min;
                bestSec = sec;
            }
        }
        System.out.printf("With %d minutes and %d seconds %s is the winner of the day!\n", bestMin, bestSec, winnersName);
        System.out.printf("Today's prizes are %d Gold %d Silver and %d Bronze cards!", gold, silver, bronze);
    }
}
