package MoreExercise;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WinningTicket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Pattern pattern = Pattern.compile("(?=(?<ch>[@#$^]))(?<match>\\k<ch>{6,}).*(?<=.{10})\\k<match>.*");
        String[] tickets = scanner.nextLine().split("\\s*,\\s*");

        for (String ticket : tickets) {
            if (ticket.length() == 20) {
                Matcher matcher = pattern.matcher(ticket);
                if (matcher.find()) {
                    System.out.printf("ticket \"%s\" - %d%s", ticket, matcher.group("match").length(), matcher.group("ch"));
                    if (matcher.group("match").length() == 10) {
                        System.out.println(" Jackpot!");
                    } else {
                        System.out.println();
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