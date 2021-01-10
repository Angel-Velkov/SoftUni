package MoreExercise;

import java.util.Arrays;
import java.util.Scanner;

public class TreasureFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] key = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        String line = scanner.nextLine();
        while (!line.equals("find")) {
            StringBuilder cryptMessage = new StringBuilder();
            cryptMessage.append(line);
            int keyNumber = 0;
            for (int i = 0; i < cryptMessage.length(); i++) {
                cryptMessage.setCharAt(i, (char) (cryptMessage.charAt(i) - key[keyNumber]));
                if (keyNumber == key.length - 1) {
                    keyNumber = 0;
                } else {
                    keyNumber++;
                }
            }
            String type;
            int startOfTheType = cryptMessage.indexOf("&") + 1;
            int endOfTheType = cryptMessage.indexOf("&", startOfTheType);
            if (startOfTheType != -1 && endOfTheType != -1) {
                type = cryptMessage.substring(startOfTheType, endOfTheType);
            } else {
                type = "{No type}";
            }

            String coordinates;
            int beginningOfCoordinates = cryptMessage.indexOf("<") + 1;
            int endingOfCoordinates = cryptMessage.indexOf(">");
            if (beginningOfCoordinates != -1 && beginningOfCoordinates < endingOfCoordinates && endingOfCoordinates != -1) {
                coordinates = cryptMessage.substring(beginningOfCoordinates, endingOfCoordinates);
            } else {
                coordinates = "{No coordinates}";
            }

            System.out.println("Found " + type + " at " + coordinates);

            line = scanner.nextLine();
        }
    }
}
