package Exercise;

import java.util.Scanner;

public class OnTimeForTheExam {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int hour = Integer.parseInt(scan.nextLine());
        int min = Integer.parseInt(scan.nextLine());
        int hourArrived = Integer.parseInt(scan.nextLine());
        int minArrived = Integer.parseInt(scan.nextLine());

        int totalMins = hour * 60 + min;
        int arrivedMins = hourArrived * 60 + minArrived;
        int difference = totalMins - arrivedMins;
        int h = Math.abs(difference / 60);
        int m = Math.abs(difference % 60);

        if (totalMins >= arrivedMins) {
            if (difference <= 30) {
                System.out.println("On time");
            } else {
                System.out.println("Early");
            }
            if (difference != 0 && difference < 60) {
                System.out.printf("%d minutes before the start", difference);
            } else if (difference >= 60) {
                System.out.printf("%d:%02d hours before the start", h, m);
            }
        } else {
            System.out.println("Late");
            if (Math.abs(difference) < 60) {
                System.out.printf("%d minutes after the start", Math.abs(difference));
            } else {
                System.out.printf("%d:%02d hours after the start", h, m);
            }
        }
    }
}
