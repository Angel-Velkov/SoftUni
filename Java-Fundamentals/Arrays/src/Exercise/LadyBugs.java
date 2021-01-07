package Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class LadyBugs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int[] ladybugsPositions = new int[size];
        int[] ladybugsIndexes = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        for (int index : ladybugsIndexes) {
            if (0 <= index && index < ladybugsPositions.length) {
                ladybugsPositions[index] = 1;
            }
        }

        String command = scanner.nextLine();
        while (!command.equals("end")) {
            String[] commandParameters = command.split("\\s+");

            int currentIndex = Integer.parseInt(commandParameters[0]);
            String direction = commandParameters[1];
            int flyLength = Integer.parseInt(commandParameters[2]);
            if ((0 <= currentIndex && currentIndex < ladybugsPositions.length)
                    && ladybugsPositions[currentIndex] == 1) {
                switch (direction) {
                    case "right": {
                        ladybugsPositions[currentIndex] = 0;
                        int landingPosition = currentIndex + flyLength;

                        while (0 <= landingPosition && landingPosition < ladybugsPositions.length) {
                            if (ladybugsPositions[landingPosition] == 1) {
                                landingPosition += flyLength;
                            } else {
                                ladybugsPositions[landingPosition] = 1;
                                break;
                            }
                        }
                    }
                    break;
                    case "left": {
                        ladybugsPositions[currentIndex] = 0;
                        int landingPosition = currentIndex - flyLength;

                        while (0 <= landingPosition && landingPosition < ladybugsPositions.length) {
                            if (ladybugsPositions[landingPosition] == 1) {
                                landingPosition -= flyLength;
                            } else {
                                ladybugsPositions[landingPosition] = 1;
                                break;
                            }
                        }
                    }
                    break;
                    default:
                        System.out.println("Invalid direction!");
                }
            }

            command = scanner.nextLine();
        }
        for (int ladybug : ladybugsPositions) {
            System.out.print(ladybug + " ");
        }
    }
}
