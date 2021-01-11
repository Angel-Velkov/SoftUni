package August2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DestinationMapper {
    public static void main(String[] args) {

        String regex = "([=/])(?<destination>[A-Z][A-Za-z]{2,})\\1";
        Pattern pattern = Pattern.compile(regex);

        String line = new Scanner(System.in).nextLine();

        Matcher matcher = pattern.matcher(line);
        List<String> destinations = new ArrayList<>();
        while (matcher.find()) {
            destinations.add(matcher.group("destination"));
        }

        System.out.println("Destinations: " + String.join(", ", destinations));

        System.out.println("Travel Points: " + destinations.stream().mapToInt(l -> l.length()).sum());
    }
}
