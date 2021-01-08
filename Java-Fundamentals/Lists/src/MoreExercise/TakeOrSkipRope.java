package MoreExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TakeOrSkipRope {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String cryptMassage = scanner.nextLine();
        List<Integer> digits = new ArrayList<>();
        List<Character> s = new ArrayList<>();
        List<Character> encryptMassage = new ArrayList<>();

        for (int i = 0; i < cryptMassage.length(); i++) {
            char currentChar = cryptMassage.charAt(i);
            if (Character.isDigit(currentChar)) {
                digits.add(Integer.parseInt(currentChar + ""));
            } else {
                s.add(currentChar);
            }
        }

        for (int i = 0; i < digits.size(); i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < digits.get(i); j++) {
                    if (s.isEmpty()) {
                        break;
                    }
                    encryptMassage.add(s.remove(0));
                }
            } else {
                for (int j = 0; j < digits.get(i); j++) {
                    if (s.isEmpty()) {
                        break;
                    }
                    s.remove(0);
                }
            }
        }

        for (Character character : encryptMassage) {
            System.out.print(character);
        }
    }
}
