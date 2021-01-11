package December2020;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        String command = scanner.nextLine();
        while (!command.equals("Done")) {
            String[] commandParameters = command.split("\\s+");
            switch (commandParameters[0]) {
                case "Change": {
                    char target = commandParameters[1].charAt(0);
                    char replacement = commandParameters[2].charAt(0);
                    line = line.replace(target, replacement);

                    System.out.println(line);
                    break;
                }
                case "Includes": {
                    String includingString = commandParameters[1];
                    if (line.contains(includingString)){
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
                    break;
                }
                case "End": {
                    String endingString = commandParameters[1];
                    int sbstrStrat = line.length()-endingString.length();
                    String lineEndsWith = line.substring(sbstrStrat);
                    if (lineEndsWith.equals(endingString)){
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
                    break;
                }
                case "Uppercase": {
                    line = line.toUpperCase();
                    System.out.println(line);
                    break;
                }
                case "FindIndex": {
                    char searchingCharacter = commandParameters[1].charAt(0);
                    int foundAtIndex = line.indexOf(searchingCharacter);

                    if (foundAtIndex != -1){
                        System.out.println(foundAtIndex);
                    }
                    break;
                }
                case "Cut": {
                    int startIndex = Integer.parseInt(commandParameters[1]) ;
                    int length = Integer.parseInt(commandParameters[2]);
                    line = line.substring(startIndex,startIndex + length);
                    System.out.println(line);
                    break;
                }
            }

            command = scanner.nextLine();
        }
    }
}
/*
//Th1s 1s my str1ng!//
Change 1 i
Includes string
End my
Uppercase
FindIndex I
Cut 5 5
Done
 */