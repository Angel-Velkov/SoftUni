package Exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AnonymousThreat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> input = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));
        String command = scanner.nextLine();

        while (!command.equals("3:1")) {
            String[] commandAsArray = command.split(" ");

            if (commandAsArray[0].equals("merge")) {
                int startIndex = Integer.parseInt(commandAsArray[1]);
                int endIndex = Integer.parseInt(commandAsArray[2]);
                mergeElementsInArr(input, startIndex, endIndex);

            } else if (commandAsArray[0].equals("divide")) {
                int index = Integer.parseInt(commandAsArray[1]);
                int partition = Integer.parseInt(commandAsArray[2]);
                dividesIntoEqualsPieces(input, index, partition);
            }
            command = scanner.nextLine();
        }
        System.out.println(String.join(" ", input));
    }

    private static void dividesIntoEqualsPieces(List<String> input, int index, int partition) {
        if (index >= 0 && index < input.size() && partition >= 0 && partition <= 100) {
            String element = input.get(index);
            List<String> newList = new ArrayList<>();

            int portionLength = element.length() / partition;
            int count = 0;

            if (element.length() % partition == 0) {
                for (int i = 0; i < partition; i++) {
                    String concat = "";
                    for (int j = 0; j < portionLength; j++) {
                        char symbol = element.charAt(count);
                        concat += symbol;
                        count++;
                    }
                    newList.add(concat);
                }
            } else {
                for (int i = 0; i < partition; i++) {
                    String concat = "";
                    if (i == partition - 1) {
                        for (int j = count; j < element.length(); j++) {
                            char symbol = element.charAt(count);
                            concat += symbol;
                            count++;
                        }
                    } else {
                        for (int j = 0; j < portionLength; j++) {
                            char symbol = element.charAt(count);
                            concat += symbol;
                            count++;
                        }
                    }
                    newList.add(concat);
                }
            }
            input.remove(index);
            input.addAll(index, newList);
        }
    }

    private static void mergeElementsInArr(List<String> input, int startIndex, int endIndex) {
        startIndex = Math.max(0, startIndex);
        endIndex = Math.min(input.size() - 1, endIndex);

        for (int i = startIndex; i < endIndex; i++) {
            String concat = "";
            concat += input.get(startIndex) + input.get(startIndex + 1);
            input.set(startIndex, concat);
            input.remove(startIndex + 1);
        }
    }
}
