package Exercise;

import java.util.Scanner;

public class PrintAndSum {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int start = scan.nextInt();
        int end = scan.nextInt();
        int sum = 0;

        for (int go = start; go <= end; go++) {
            System.out.print(go + " ");
            sum += go;
        }
        System.out.println();
        System.out.printf("Sum: %d", sum);
    }
}
