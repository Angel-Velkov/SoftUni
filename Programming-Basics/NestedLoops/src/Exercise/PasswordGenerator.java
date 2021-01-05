package Exercise;

import java.util.Scanner;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int a = Integer.parseInt(scan.nextLine());
        int b = Integer.parseInt(scan.nextLine());

        for (int i = 1; i < a; i++) {
            for (int j = 1; j < a; j++) {
                for (char k = 'a'; k < 'a' + b; k++) {
                    for (char l = 'a'; l < 'a' + b; l++) {
                        for (int m = 2; m <= a; m++) {
                            if (m > i && m > j) {
                                System.out.printf("%d%d%c%c%d ", i, j, k, l, m);
                            }
                        }
                    }
                }
            }
        }
    }
}
