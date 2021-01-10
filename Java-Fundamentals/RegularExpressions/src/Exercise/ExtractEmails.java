package Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractEmails {
    public static void main(String[] args) {
        List<String> validEmails = new ArrayList<>();

        String regex = "(?<name>(^|(?<=\\s))[A-Za-z0-9]+[.\\-_]?[A-Za-z0-9]+)@(?<host>([A-Za-z]+([.\\-][A-Za-z]+)+)(\\b|(?=\\s)))";
        Pattern pattern = Pattern.compile(regex);

        String line = new Scanner(System.in).nextLine();

        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            validEmails.add(matcher.group());
        }

        validEmails.forEach(System.out::println);
    }
}
