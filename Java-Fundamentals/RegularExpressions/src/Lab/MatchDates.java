package Lab;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchDates {
    public static void main(String[] args) {

        String regex = "\\b(?<day>([1-3][0-9]|0[1-9]))([./-])(?<month>([A-Z][a-z]{2}))\\3(?<year>\\d{4})\\b";
        String dateString = new Scanner(System.in).nextLine();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateString);

        while (matcher.find()) {
            System.out.printf("Day: %s, Month: %s, Year: %s%n"
                    , matcher.group("day"), matcher.group("month"), matcher.group("year"));
        }
    }
}
