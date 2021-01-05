package Exercise;

import java.util.Scanner;

public class Cake {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int wight = Integer.parseInt(scan.nextLine());
        int length = Integer.parseInt(scan.nextLine());
        String input = scan.nextLine();
        int cake = wight * length;

        while (!input.equals("STOP")) {
            int piece = Integer.parseInt(input);
            cake -= piece;
            if (cake <= 0) {
                break;
            }
            input = scan.nextLine();
        }
        if (cake < 0) {
            System.out.printf("No more cake left! You need %d pieces more.", Math.abs(cake));
        } else {
            System.out.printf("%d pieces are left.", cake);
        }
    }
}
