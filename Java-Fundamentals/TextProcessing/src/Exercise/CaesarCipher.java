package Exercise;

import java.util.Scanner;

public class CaesarCipher {
    public static void main (String[] args){
        StringBuilder text = new StringBuilder(new Scanner(System.in).nextLine());

        for (int i = 0; i < text.length(); i++) {
            text.setCharAt(i, (char)(text.charAt(i) + 3));
        }
        System.out.println(text);
    }
}
