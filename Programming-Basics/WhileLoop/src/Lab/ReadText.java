package Lab;

import java.util.Scanner;

public class ReadText {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            String name = scan.nextLine();
            if (name.equals("Stop")) {
                break;
            }
            System.out.println(name);
        }
    }
}
