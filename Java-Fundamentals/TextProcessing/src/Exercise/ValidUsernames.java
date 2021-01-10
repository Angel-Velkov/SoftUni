package Exercise;

import java.util.Scanner;

public class ValidUsernames {
    public static void main(String[] args) {

        String[] usernames = new Scanner(System.in).nextLine().split(", ");

        for (String username : usernames) {
            if (isValidUsername(username)) {
                System.out.println(username);
            }
        }
    }

    private static boolean isValidUsername(String username) {
        if (3 > username.length() || username.length() > 16) {
            return false;
        }
        for (int i = 0; i < username.length(); i++) {
            char symbol = username.charAt(i);
            if (!Character.isLetterOrDigit(symbol) && symbol != '-' && symbol != '_') {
                return false;
            }
        }
        return true;
    }
}
