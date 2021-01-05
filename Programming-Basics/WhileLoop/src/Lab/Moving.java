package Lab;

import java.util.Scanner;

public class Moving {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int width = Integer.parseInt(scan.nextLine());
        int length = Integer.parseInt(scan.nextLine());
        int height = Integer.parseInt(scan.nextLine());
        String input = scan.nextLine();

        int volume = width * height * length;

        while (!input.equals("Done")) {
            int boxes = Integer.parseInt(input);
            volume -= boxes;
            if (volume < 0) {
                System.out.printf("No more free space! You need %d Cubic meters more.", Math.abs(volume));
                return;
            }
            input = scan.nextLine();
        }
        System.out.printf("%d Cubic meters left.", volume);
    }
}
