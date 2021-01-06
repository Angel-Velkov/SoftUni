package MoreExercise;

import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String normal = scan.nextLine();
        String reversed = "";

        for (int i = normal.length() - 1; i >= 0; i--) {
            reversed += normal.charAt(i);
        }
        System.out.println(reversed);
    }
}
