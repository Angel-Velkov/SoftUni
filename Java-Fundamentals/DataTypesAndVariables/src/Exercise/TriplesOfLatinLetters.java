package Exercise;

import java.util.Scanner;

public class TriplesOfLatinLetters {
    public static void main(String[] args) {

        int n = new Scanner(System.in).nextInt();

        for (int i = 'a'; i < 'a' + n; i++) {
            for (int j = 'a'; j < 'a' + n; j++) {
                for (int k = 'a'; k < 'a' + n; k++) {
                    System.out.printf("%c%c%c%n", i, j, k);
                }
            }
        }
    }
}
