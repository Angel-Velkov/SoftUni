package April2020;

import java.util.Scanner;

public class PasswordReset {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder password = new StringBuilder(scanner.nextLine());

        String command = scanner.nextLine();
        while (!command.equals("Done")) {
            String[] commandParameters = command.split("\\s+");

            switch (commandParameters[0]) {
                case "TakeOdd":
                    removeEvenCharacters(password);
                    System.out.println(password);
                    break;
                case "Cut":
                    int index = Integer.parseInt(commandParameters[1]);
                    int length = Integer.parseInt(commandParameters[2]) + index;
                    password.replace(index, length, "");
                    System.out.println(password);
                    break;
                case "Substitute":
                    String substring = commandParameters[1];
                    String substitute = commandParameters[2];

                    if (password.toString().contains(substring)){
                        password = new StringBuilder(password.toString().replace(substring, substitute));
                        System.out.println(password);
                    } else {
                        System.out.println("Nothing to replace!");
                    }
                    break;
            }

            command = scanner.nextLine();
        }
        System.out.println("Your password is: " + password);
    }

    private static void removeEvenCharacters(StringBuilder line) {
        for (int i = 0; i < line.length(); i++) {
            line.deleteCharAt(i);
        }
    }
}
