import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {

        String expression = "\\b(?<day>\\d\\d?)-(?<month>[A-Z][a-z]{2})-(?<year>\\d{4})\\b";
        String text = "I am born on 30-Dec-1994.\n" +
                "My father is born on the 9-Jul-1955.\n" +
                "01-July-2000 is not a valid date.";

        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println("Day: " + matcher.group("day"));
            System.out.println("Month" + matcher.group("month"));
            System.out.println("year");
        }
    }
}
