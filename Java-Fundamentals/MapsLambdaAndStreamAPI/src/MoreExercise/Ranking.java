package MoreExercise;

import java.util.*;

public class Ranking {
    public static void main(String[] args) {
        Map<String, String> contests = new HashMap<>();
        Map<String, Map<String, Integer>> users = new TreeMap<>();

        Scanner scanner = new Scanner(System.in);

        String firstInput = scanner.nextLine();
        while (!firstInput.equals("end of contests")) {
            String[] contestNameAndPassword = firstInput.split(":");
            String contestName = contestNameAndPassword[0];
            String password = contestNameAndPassword[1];
            contests.put(contestName, password);
            firstInput = scanner.nextLine();
        }

        String secondInput = scanner.nextLine();
        while (!secondInput.equals("end of submissions")) {
            String[] participantData = secondInput.split("=>");
            String contestName = participantData[0];
            String password = participantData[1];
            String username = participantData[2];
            int points = Integer.parseInt(participantData[3]);

            if (contests.containsKey(contestName) && contests.get(contestName).equals(password)) {
                Map<String, Integer> course = new HashMap<>();
                course.put(contestName, points);
                if (!users.containsKey(username)) {
                    users.put(username, course);
                } else {
                    if (!users.get(username).containsKey(contestName)) {
                        users.get(username).put(contestName, points);
                    } else {
                        users.get(username).put(contestName, Math.max(points, users.get(username).get(contestName)));
                    }
                }
            }
            secondInput = scanner.nextLine();
        }

        int bestTotalScore = 0;
        String student = "";
        for (Map.Entry<String, Map<String, Integer>> user : users.entrySet()) {
            int totalsScore = 0;
            for (Integer score : user.getValue().values()) {
                totalsScore += score;
            }
            if (totalsScore > bestTotalScore) {
                bestTotalScore = totalsScore;
                student = user.getKey();
            }

        }

        System.out.printf("Best candidate is %s with total %d points.%n", student, bestTotalScore);

        System.out.println("Ranking: ");
        users.forEach((key, value) -> {
            System.out.println(key);
            value
                    .entrySet()
                    .stream()
                    .sorted((c1, c2) -> Integer.compare(c2.getValue(), c1.getValue()))
                    .forEach(c -> System.out.println("#  " + c.getKey() + " -> " + c.getValue()));
        });
    }
}
