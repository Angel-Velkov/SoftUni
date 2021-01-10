package MoreExercise;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WinningTicket {
    public static void main(String[] args) {
        String regex = "@{6,10}|#{6,10}|\\${6,10}|\\^{6,10}";
        Pattern pattern = Pattern.compile(regex);
        String[] input = new Scanner(System.in).nextLine().split("\\s*,\\s+");

        for (String ticket : input) {
            if (ticket.length() == 20) {
                String leftSide = ticket.substring(0, 10);
                String rightSide = ticket.substring(10);

                Matcher leftMatcher = pattern.matcher(leftSide);
                Matcher rightMatcher = pattern.matcher(rightSide);

                if (leftMatcher.find() && rightMatcher.find()) {
                    String leftSequence = leftMatcher.group();
                    String rightSequence = rightMatcher.group();
                    int shorterSequence = Math.min(leftSequence.length(), rightSequence.length());

                    if (leftSequence.length() == 10 && leftSequence.equals(rightSequence)) {
                        System.out.printf("ticket \"%s\" - %d%c Jackpot!%n", ticket, shorterSequence, leftSequence.charAt(0));
                    } else if (leftSequence.charAt(0) == rightSequence.charAt(0)) {
                        System.out.printf("ticket \"%s\" - %d%c%n", ticket, shorterSequence, rightSequence.charAt(0));
                    } else {
                        System.out.printf("ticket \"%s\" - no match%n", ticket);
                    }
                } else {
                    System.out.printf("ticket \"%s\" - no match%n", ticket);
                }
            } else {
                System.out.println("invalid ticket");
            }
        }
    }
}

//TODO: Оправи решението!!! (Знаеш, че е грозно) - Може като на Test 2