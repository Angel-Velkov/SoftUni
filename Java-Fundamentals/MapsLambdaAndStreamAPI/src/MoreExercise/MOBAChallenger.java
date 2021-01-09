package MoreExercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MOBAChallenger {

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> players = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while (!input.equals("Season end")) {

            if (input.contains(" -> ")) {
                String[] playerData = input.split(" -> ");
                String player = playerData[0];
                String position = playerData[1];
                int skill = Integer.parseInt(playerData[2]);

                if (!players.containsKey(player)) {
                    Map<String, Integer> positionsAndSkills = new HashMap<>();
                    positionsAndSkills.put(position, skill);
                    players.put(player, positionsAndSkills);
                } else if (!players.get(player).containsKey(position)) {
                    players.get(player).put(position, skill);
                } else if (players.get(player).get(position) < skill) {
                    players.get(player).put(position, skill);
                }

            } else if (input.contains(" vs ")) {
                String[] rivals = input.split(" vs ");

                Map<String, Integer> firstPlayer = players.get(rivals[0]);
                Map<String, Integer> secondPlayer = players.get(rivals[1]);

                if (thereWillBeADuel(firstPlayer, secondPlayer)) {
                    int firstPlayerTotalPoints = getTotalSkillPoints(firstPlayer);
                    int secondPlayerTotalPoints = getTotalSkillPoints(secondPlayer);

                    if (firstPlayerTotalPoints > secondPlayerTotalPoints) {
                        players.remove(rivals[1]);
                    } else if (firstPlayerTotalPoints < secondPlayerTotalPoints) {
                        players.remove(rivals[0]);
                    }
                }
            }
            input = scanner.nextLine();
        }
        printPlayersData(players);
    }

    private static void printPlayersData(Map<String, Map<String, Integer>> players) {
        players.entrySet()
                .stream()
                .sorted((p1, p2) -> {
                    int result = Integer.compare(getTotalSkillPoints(p2.getValue()), getTotalSkillPoints(p1.getValue()));
                    if (result == 0) {
                        result = p1.getKey().compareTo(p2.getKey());
                    }
                    return result;
                })
                .forEach(p -> {
                    System.out.printf("%s: %d skill%n", p.getKey(), getTotalSkillPoints(p.getValue()));
                    p.getValue()
                            .entrySet()
                            .stream()
                            .sorted((a, b) -> {
                                int result = Integer.compare(b.getValue(), a.getValue());
                                if (result == 0) {
                                    result = a.getKey().compareTo(b.getKey());
                                }
                                return result;
                            })
                            .forEach(position -> System.out.printf("- %s <::> %d%n"
                                    , position.getKey(), position.getValue()));
                });
    }

    private static boolean thereWillBeADuel(Map<String, Integer> firstPlayer, Map<String, Integer> secondPlayer) {
        if (firstPlayer == null || secondPlayer == null) {
            return false;
        }
        for (String p1 : firstPlayer.keySet()) {
            for (String p2 : secondPlayer.keySet()) {
                if (p1.equals(p2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int getTotalSkillPoints(Map<String, Integer> playerData) {
        int totalPoints = 0;
        for (Map.Entry<String, Integer> position : playerData.entrySet()) {
            totalPoints += position.getValue();
        }
        return totalPoints;
    }
}
