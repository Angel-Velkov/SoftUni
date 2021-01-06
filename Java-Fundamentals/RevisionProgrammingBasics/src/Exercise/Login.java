package Exercise;

import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String username = scan.nextLine();
        String password = "";
        boolean successfully = false;

        for (int i = username.length() - 1; i >= 0; i--) {
            password += username.charAt(i);
        }
        for (int i = 0; i < 4; i++) {
            String input = scan.nextLine();
            if (input.equals(password)) {
                successfully = true;
                System.out.printf("User %s logged in.", username);
                break;
            }
            if (i < 3) {
                System.out.println("Incorrect password. Try again.");
            }
        }
        if (!successfully) {
            System.out.printf("User %s blocked!", username);
        }
    }
}
