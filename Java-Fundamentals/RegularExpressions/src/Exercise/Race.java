package Exercise;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Race {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Pattern namePattern = Pattern.compile("[A-Za-z]");
        Pattern distancePattern = Pattern.compile("\\d");

        String[] names = scanner.nextLine().split(", ");
        Map<String, Integer> racers = new LinkedHashMap<>();
        for (String name : names) {
            racers.put(name, 0);
        }

        String input;

        while (!"end of race".equals(input = scanner.nextLine())) {
            StringBuilder name = new StringBuilder();
            Matcher nameMatcher = namePattern.matcher(input);

            while (nameMatcher.find()) {
                name.append(nameMatcher.group());
            }

            if (racers.containsKey(name.toString())) {
                int currentDistance = racers.get(name.toString());

                Matcher distanceMatcher = distancePattern.matcher(input);
                while (distanceMatcher.find()) {
                    currentDistance += Integer.parseInt(distanceMatcher.group());
                }

                racers.put(name.toString(), currentDistance);
            }
        }

        List<String> winners = racers.entrySet()
                .stream()
                .sorted((r1, r2) -> Integer.compare(r2.getValue(), r1.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("1st place: " + winners.get(0));
        System.out.println("2nd place: " + winners.get(1));
        System.out.println("3rd place: " + winners.get(2));
    }
}
