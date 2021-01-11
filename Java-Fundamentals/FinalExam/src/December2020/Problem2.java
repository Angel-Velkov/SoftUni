package December2020;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Pattern tagPattern = Pattern.compile("^([$%])(?<tag>[A-Z][a-z]{2,})\\1$");
        Pattern colonsPattern = Pattern.compile("^(\\[(?<firstNumber>\\d+)]\\|)(\\[(?<secondNumber>\\d+)]\\|)(\\[(?<thirdNumber>\\d+)]\\|)$");

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            boolean flag = true;
            String[] input= scanner.nextLine().split(": ");
            String tag = input[0];

            Matcher tagMatcher = tagPattern.matcher(tag);
            if (!tagMatcher.find()){
                flag = false;
            }

            String colons = input[1];
            Matcher colonsMatcher = colonsPattern.matcher(colons);

            if (!colonsMatcher.find()){
                flag = false;
            }

            if (!flag){
                System.out.println("Valid message not found!");
            } else {
                StringBuilder encryptMessage = new StringBuilder();
                encryptMessage.append((char) Integer.parseInt(colonsMatcher.group("firstNumber")));
                encryptMessage.append((char) Integer.parseInt(colonsMatcher.group("secondNumber")));
                encryptMessage.append((char) Integer.parseInt(colonsMatcher.group("thirdNumber")));

                System.out.printf("%s: %s%n", tagMatcher.group("tag"), encryptMessage.toString());
            }
        }
    }
}
/*
4
$Request$: [73]|[115]|[105]|
%Taggy$: [73]|[73]|[73]|
%Taggy%: [118]|[97]|[108]|
$Request$: [73]|[115]|[105]|[32]|[75]|

 */
