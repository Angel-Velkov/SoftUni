package Exercise;

import java.util.Scanner;

public class SumSeconds {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Integer firstTime = Integer.parseInt(scan.nextLine());
        Integer secondTime = Integer.parseInt(scan.nextLine());
        Integer thirdTime = Integer.parseInt(scan.nextLine());

        int totalTime = firstTime + secondTime + thirdTime;

        int min = totalTime / 60;
        int sec = totalTime % 60;

        if (sec < 10) {
            System.out.printf("%d:0%d", min, sec);
        } else {
            System.out.printf("%d:%d", min, sec);
        }
    }
}
