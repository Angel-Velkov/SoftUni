package MoreExercise;

import java.util.Scanner;

public class WinningTicket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s*,\\s+");
        for (String ticket : input) {
            if (ticket.length() == 20) {
                String leftHalf = ticket.substring(0, 10);
                String rightHalf = ticket.substring(10);

                int sequence = Math.min(countOfSequenceOfSymbols(leftHalf), countOfSequenceOfSymbols(rightHalf));

                if (6 <= sequence && sequence <= 9) {
                    System.out.printf("ticket \"%s\" - %d%c%n", ticket, sequence, ticket.charAt(5));
                } else if (sequence == 10) {
                    System.out.printf("ticket \"%s\" - %d%c Jackpot!%n", ticket, sequence, ticket.charAt(5));
                } else {
                    System.out.printf("ticket \"%s\" - no match%n", ticket);
                }
            } else {
                System.out.println("invalid ticket");
            }
        }
    }

    private static int countOfSequenceOfSymbols(String line) {
        int maxCount = 0;
        int count = 0;
        char previousChar;
        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);
            switch (currentChar) {
                case '@':
                case '#':
                case '$':
                case '^':
                    if (count == 0) {
                        previousChar = currentChar;
                    } else {
                        previousChar = line.charAt(i - 1);
                    }

                    if (currentChar == previousChar) {
                        count++;
                    } else {
                        if (maxCount < count) {
                            maxCount = count;
                            count = 1;
                        }
                    }
                    break;

                default:
                    if (count != 0) {
                        if (maxCount < count) {
                            maxCount = count;
                            count = 0;
                        }
                    }
            }
        }
        if (maxCount < count) {
            maxCount = count;
        }
        return maxCount;
    }
}
