package Exercise;

import java.util.Scanner;

public class NxNMatrix {
    public static void main(String[] args) {

        int input = Integer.parseInt(new Scanner(System.in).nextLine());

        printCubeFromNumbers(input);
    }

    private static void printCubeFromNumbers(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(n);
                if (j + 1 < n)
                    System.out.print(" ");
            }
            System.out.println();

        }
    }
}
