package Exercise;

import java.util.Scanner;

public class Train {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        int[] people = new int[n];
        int sum = 0;

        for (int i = 0; i < people.length; i++) {
            people[i] = Integer.parseInt(scan.nextLine());
            sum += people[i];
        }
        for (int i = 0; i < people.length; i++) {
            System.out.print(people[i] + " ");
        }
        System.out.println();
        System.out.print(sum);
    }
}
