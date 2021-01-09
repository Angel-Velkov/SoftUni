package Exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SoftUniParking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> users = new LinkedHashMap<>();

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split(" ");

            String command = line[0];
            String username = line[1];

            switch (command) {
                case "register":
                    String plateNumber = line[2];

                    if (users.containsKey(username)) {
                        System.out.printf("ERROR: already registered with plate number %s%n", plateNumber);
                    } else {
                        users.put(username, plateNumber);
                        System.out.printf("%s registered %s successfully%n", username, plateNumber);
                    }
                    break;
                case "unregister":
                    if (users.containsKey(username)) {
                        users.remove(username);
                        System.out.printf("%s unregistered successfully%n", username);
                    } else {
                        System.out.printf("ERROR: user %s not found%n", username);
                    }
                    break;
            }
        }
        users.forEach((k, v) -> System.out.println(k + " => " + v));
    }
}
