package MoreExercise;

import java.util.*;

public class Judge {
    public static void main(String[] args) {
        Map<String, Map<String, Integer>> contests = new LinkedHashMap();
        Map<String, Integer> allStudents = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        String input;
        while (!"no more time".equals(input = scanner.nextLine())) {
            String[] userData = input.split(" -> ");
            String username = userData[0];
            String contestName = userData[1];
            int currentPoints = Integer.parseInt(userData[2]);

            Map<String, Integer> userScore = new HashMap<>();
            if (!contests.containsKey(contestName)) {
                contests.put(contestName, userScore);
            }

            Integer points = contests.get(contestName).get(username);
            if (points == null) {
                points = 0;
            }
            if (points < currentPoints || currentPoints == 0) {
                contests.get(contestName).put(username, currentPoints);

                Integer totalPoints = allStudents.get(username);
                if (totalPoints == null) {
                    totalPoints = 0;
                }
                allStudents.put(username, totalPoints + (currentPoints - points));
            }
        }
        int[] num = {1};
        contests.forEach((key, value) -> {
            num[0] = 0;
            System.out.println(key + ": " + value.size() + " participants");
            value
                    .entrySet()
                    .stream()
                    .sorted((s1, s2) -> {
                        if (!s1.getValue().equals(s2.getValue())) {
                            return Integer.compare(s2.getValue(), s1.getValue());
                        }
                        return s1.getKey().compareTo(s2.getKey());
                    })
                    .forEach(s -> System.out.printf("%d. %s <::> %d%n", ++num[0], s.getKey(), s.getValue()));
        });
        num[0] = 0;
        System.out.println("Individual standings:");
        allStudents
                .entrySet()
                .stream()
                .sorted((s1, s2) -> {
                    if (!s1.getValue().equals(s2.getValue())) {
                        return Integer.compare(s2.getValue(), s1.getValue());
                    }
                    return s1.getKey().compareTo(s2.getKey());
                })
                .forEach(s -> System.out.printf("%d. %s -> %d%n", ++num[0], s.getKey(), s.getValue()));
    }
}
