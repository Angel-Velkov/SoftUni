package Exercise;

import java.util.Scanner;

public class Ages {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int ages = scan.nextInt();
        String type;

        if (ages > 65) {
            type = "elder";
        } else if (ages > 19) {
            type = "adult";
        } else if (ages > 13) {
            type = "teenager";
        } else if (ages > 2) {
            type = "child";
        } else {
            type = "baby";
        }
        System.out.println(type);
    }

}
