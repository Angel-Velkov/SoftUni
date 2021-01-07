package Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class KaminoFactory3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int length = Integer.parseInt(scanner.nextLine());
        String input;

        int bestSequence = 0;
        int[] bestDNA = new int[length];
        int bestStartIndex = -1;
        int lineCounter = 0;
        int bestLine = 0;

        int bestSum = 0;

        while (!"Clone them!".equals(input = scanner.nextLine())) {
            int[] DNA = Arrays.stream(input.split("!+")).mapToInt(Integer::parseInt).toArray();
            lineCounter++;

            int longestOnceInARow = 0;
            int startIndex = -1;
            int DNASum = 0;
            int bestDNASum = 0;
            for (int i = 0; i < DNA.length; i++) {
                int onceInARow = 0;
                for (int j = i; j < DNA.length; j++) {
                    if (DNA[i] == DNA[j] && DNA[j] == 1) {
                        onceInARow++;
                        if (onceInARow > longestOnceInARow) {
                            longestOnceInARow = onceInARow;
                            startIndex = i;
                        }
                    } else {
                        break;
                    }
                }
            }

            if (longestOnceInARow > bestSequence) {
                bestSequence = longestOnceInARow;
                bestStartIndex = startIndex;
                bestLine = lineCounter;
            } else if (longestOnceInARow == bestSequence) {
                if (bestStartIndex > startIndex) {
                    bestDNA = DNA;
                    bestStartIndex = startIndex;
                    bestLine = lineCounter;
                } else if (bestStartIndex == startIndex) {

                    for (int num : bestDNA){
                        bestSum += num;
                    }

                    for (int num : DNA) {
                        DNASum += num;
                    }

                    if (DNASum > bestDNASum) {
                        bestDNA = DNA;
                        bestSum = DNASum;
                        bestLine = lineCounter;
                    }
                }
            }
        }
        bestSum = 0;
        for (int n : bestDNA) {
            bestSum += n;
        }
        if (bestSum == 0) {
            bestLine = 1;
        }
        System.out.printf("Best DNA sample %d with sum: %d.%n", bestLine, bestSum);
        for (int n : bestDNA) {
            System.out.print(n + " ");
        }
    }
}
/*
6
0!0!0!0!1!1!
0!0!0!1!1!0!
0!0!0!1!1!1!
0!1!1!0!1!1!
0!0!1!1!1!1!
1!1!1!0!1!1!
1!0!0!0!0!0!
Clone them!

 */

/*
6
0!1!1!0!0!0!
0!1!1!0!1!1!
0!1!1!0!0!1!
Clone them!

 */