package Exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SoftUniExamResults {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> userPoints = new HashMap<>();
        Map<String, Integer> submissionsCount = new HashMap<>();

        String input;

        while (!"exam finished".equals(input = scanner.nextLine())) {
            String[] data = input.split("-");

            String name = data[0];

            if (data[1].equals("banned")) {

                userPoints.remove(name);

            } else {
                String language = data[1];
                int points = Integer.parseInt(data[2]);

                userPoints.putIfAbsent(name, 0);

                Integer maxPoints = userPoints.get(name);

                if (maxPoints < points) {
                    userPoints.put(name, points);
                }
                //-------------------------------

                Integer count = submissionsCount.get(language);

                if (count == null) {
                    count = 0;
                }

                submissionsCount.put(language, count + 1);
            }
        }
        System.out.println("Results:");

        userPoints
                .entrySet()
                .stream()
                .sorted((u1, u2) -> {
                    int result = u2.getValue() - u1.getValue();
                    if (result == 0) {
                        result = u1.getKey().compareTo(u2.getKey());
                    }
                    return result;
                }).forEach(u -> System.out.println(u.getKey() + " | " + u.getValue()));

        System.out.println("Submissions:");
        submissionsCount.entrySet()
                .stream()
                .sorted((l1, l2) -> {
                    int result = l2.getValue() - l1.getValue();
                    if (result == 0) {
                        result = l1.getKey().compareTo(l2.getKey());
                    }
                    return result;
                }).forEach(l -> System.out.println(l.getKey() + " - " + l.getValue()));
    }
}
