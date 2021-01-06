package Exercise;

import java.util.Scanner;

public class Elevator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int people = scan.nextInt();
        int capacity = scan.nextInt();

        int course = (int) Math.ceil((double) people / capacity);

        System.out.println(course);
    }
}
