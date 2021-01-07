package Exercise;

import java.util.Date;
import java.util.Scanner;

public class KaminoFactory {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int length = Integer.parseInt(scan.nextLine());
        String sequenceDNA = "";
        int currentFirstIndex = 0;
        String input = scan.nextLine();
        int bestLine = 0;
        int currentNumberLine = 0;
        int bestSequence = Integer.MIN_VALUE;
        int needIndexInLine = -1;
        int greaterSum = 0;
        int lowIndex = -1;
        Date start = new Date();
        while (!"Clone them!".equals(input)) {
            currentNumberLine++;

            String[] sequence = input.split("!+");
            int[] numberSequence = new int[length];
            for (int i = 0; i < numberSequence.length; i++) {
                numberSequence[i] = Integer.parseInt(sequence[i]);
            }

            int currentSequence = 0;
            int currentSumLine = 0;
            int longestSequence = 0;

            for (int i = 0; i < numberSequence.length; i++) {
                int currentNumber = numberSequence[i];
                currentSumLine += currentNumber;
                if (currentNumber == 1) {
                    currentSequence++;
                    currentFirstIndex = i - currentSequence + 1;
                } else {
                    currentSequence = 0;
                }
                if (currentSequence > longestSequence) {
                    longestSequence = currentSequence;
                    needIndexInLine = currentFirstIndex;
                }
            }
            if (longestSequence > bestSequence) {
                sequenceDNA = "";
                bestSequence = longestSequence;
                bestLine = currentNumberLine;
                lowIndex = needIndexInLine;
                greaterSum = currentSumLine;
                for (int i = 0; i < length; i++) {
                    sequenceDNA += numberSequence[i] + " ";
                }
            } else if (longestSequence == bestSequence) {
                if (needIndexInLine < lowIndex) {
                    sequenceDNA = "";
                    bestSequence = longestSequence;
                    bestLine = currentNumberLine;
                    lowIndex = needIndexInLine;
                    greaterSum = currentSumLine;
                    for (int i = 0; i < length; i++) {
                        sequenceDNA += numberSequence[i] + " ";
                    }
                } else if (needIndexInLine == lowIndex) {
                    if (currentSumLine > greaterSum) {
                        sequenceDNA = "";
                        bestSequence = longestSequence;
                        bestLine = currentNumberLine;
                        lowIndex = needIndexInLine;
                        greaterSum = currentSumLine;
                        for (int i = 0; i < length; i++) {
                            sequenceDNA += numberSequence[i] + " ";
                        }
                    }
                }
            }
            input = scan.nextLine();
        }
        System.out.println(String.format("Best DNA sample %d with sum: %d.", bestLine, greaterSum));
        System.out.println(sequenceDNA);
        Date end = new Date();
        System.out.println(end.getTime()- start.getTime());
    }
}