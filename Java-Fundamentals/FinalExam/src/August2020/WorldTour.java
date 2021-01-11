package August2020;

import java.util.Scanner;

public class WorldTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder allStops = new StringBuilder(scanner.nextLine());

        String line = scanner.nextLine();
        while (!line.equals("Travel")) {
            String[] data = line.split(":+");
            String command = data[0];

            switch (command) {
                case "Add Stop":
                    int index = Integer.parseInt(data[1]);
                    String insert = data[2];

                    if (isValidIndex(allStops, index)) {
                        allStops.insert(index, insert);
                    }
                    break;
                case "Remove Stop":
                    int startIndex = Integer.parseInt(data[1]);
                    int endIndex = Integer.parseInt(data[2]);

                    if (isValidIndex(allStops, startIndex) && isValidIndex(allStops, endIndex)) {
                        allStops.replace(startIndex, endIndex + 1, "");
                    }
                    break;
                case "Switch":
                    String oldString = data[1];
                    String newString = data[2];

                    allStops = new StringBuilder(allStops.toString().replace(oldString, newString));
                    //replaceOldStringWithNew(allStops, oldString, newString);
                    break;
                default:
                    return;
            }

            System.out.println(allStops.toString());
            line = scanner.nextLine();
        }
        System.out.println("Ready for world tour! Planned stops: " + allStops);
    }

    private static boolean isValidIndex(StringBuilder s, int index) {
        return 0 <= index && index < s.length();
    }

    private static void replaceOldStringWithNew(StringBuilder stops, String oldString, String newString) {
        int startIndex = stops.indexOf(oldString);
        while (startIndex != -1) {
            int endIndex = startIndex + oldString.length();
            stops.replace(startIndex, endIndex, newString);

            startIndex = stops.indexOf(oldString, endIndex);
        }
    }
}
