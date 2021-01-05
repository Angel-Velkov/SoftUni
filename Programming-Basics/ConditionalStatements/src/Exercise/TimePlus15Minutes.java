package Exercise;

import java.util.Scanner;

public class TimePlus15Minutes {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int inputHours = Integer.parseInt(scan.nextLine());
        int inputMin = Integer.parseInt(scan.nextLine());

        int min = inputHours * 60 + inputMin + 15;

        int outputHours = min / 60;
        int outputMin = min % 60;

        if (outputHours > 23) {
            System.out.printf("0:%02d", outputMin);
        } else {
            System.out.printf("%d:%02d", outputHours, outputMin);
        }
    }
}
