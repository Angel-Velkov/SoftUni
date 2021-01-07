package Lab;

import java.util.Scanner;

public class ReverseArrayOfStrings {
    public static void main(String[] args) {

        String input = new Scanner(System.in).nextLine();
        String[] arrayOfStrings = input.split(" ");
        String[] strings = new String[arrayOfStrings.length];

        for (int i = 0; i < strings.length; i++) {
            strings[i] = arrayOfStrings[i];
        }
        for (int i = 0; i < strings.length / 2; i++) {
            int swapIndex = strings.length - 1 - i;
            String oldString = strings[i];
            strings[i] = strings[swapIndex];
            strings[swapIndex] = oldString;
        }
        for (int i = 0; i < strings.length; i++) {
            System.out.print(strings[i] + " ");
        }
    }
}
