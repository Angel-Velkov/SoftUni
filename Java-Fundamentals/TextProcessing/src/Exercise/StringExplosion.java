package Exercise;

import java.util.Scanner;

public class StringExplosion {
    public static void main(String[] args) {
        StringBuilder input = new StringBuilder(new Scanner(System.in).nextLine());

        int power = 0;

        int i = 0;
        while (i < input.length()) {
            if (input.charAt(i) == '>') {
                byte explosionRange = (byte) Character.getNumericValue(input.charAt(i + 1));
                if (0 < explosionRange && explosionRange <= 9) {
                    power += explosionRange;
                }
                i++;
            } else if (power > 0 && input.charAt(i) != '>') {
                input.deleteCharAt(i);
                power--;
            } else {
                i++;
            }
        }
        System.out.println(input);
    }
}
