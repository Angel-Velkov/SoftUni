package Exercise;

import java.util.Scanner;

public class TriangleOfNumbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        for (int rows = 1; rows <= n; rows++) {
            for (int colons = 1; colons <= rows; colons++) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
}
