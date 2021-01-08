package Lab;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListManipulationBasics {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Integer> numbers = parseLineOfNumbers(scan);

        String input = "";
        while (!"end".equals(input = scan.nextLine())) {
            String[] commandArr = input.split(" ");
            String command = commandArr[0];

            switch (command) {
                case "Add":
                    int numberToAdd = Integer.parseInt(commandArr[1]);
                    numbers.add(numberToAdd);
                    break;
                case "Remove":
                    int numberToRemove = Integer.parseInt(commandArr[1]);
                    numbers.remove((Integer) numberToRemove);
                    break;
                case "RemoveAt":
                    int indexToRemove = Integer.parseInt(commandArr[1]);
                    numbers.remove(indexToRemove);
                    break;
                case "Insert":
                    int indexToInsert = Integer.parseInt(commandArr[1]);
                    int numberToInsert = Integer.parseInt(commandArr[2]);
                    numbers.add(indexToInsert, numberToInsert);
                    break;
            }
        }

        System.out.println(numbers.toString().replaceAll("[//[//],]", " "));
    }

    private static List<Integer> parseLineOfNumbers(Scanner scan) {
        String line = scan.nextLine();
        String[] numbersAsString = line.split(" ");
        List<Integer> numbers = new ArrayList<>();

        for (String s : numbersAsString) {
            int number = Integer.parseInt(s);
            numbers.add(number);
        }
        return numbers;
    }
}
