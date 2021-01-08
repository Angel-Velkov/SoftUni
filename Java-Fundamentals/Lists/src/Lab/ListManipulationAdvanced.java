package Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListManipulationAdvanced {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String line = scan.nextLine();
        String[] numbersAsString = line.split(" ");
        List<Integer> numbers = new ArrayList<>();

        for (String s : numbersAsString) {
            int number = Integer.parseInt(s);
            numbers.add(number);
        }

        String input;
        while (!"end".equals(input = scan.nextLine())) {
            String[] commandArr = input.split(" ");
            String command = commandArr[0];

            if (command.equals("Contains")) {
                int number = Integer.parseInt(commandArr[1]);
                if (checksTheListForTheNumber(numbers, number)) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No such number");
                }
            } else if (input.equals("Print even")) {
                getEvenNumbers(numbers);
            } else if (input.equals("Print odd")) {
                getOddNumbers(numbers);
            } else if (input.equals("Get sum")) {
                getSum(numbers);
            } else if (command.equals("Filter")) {
                String condition = commandArr[1];
                int finiteNumber = Integer.parseInt(commandArr[2]);
                filtrateToTheNumber(numbers, condition, finiteNumber);
            }
        }
    }

    private static boolean checksTheListForTheNumber(List<Integer> numbers, int number) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == number) {
                return true;
            }
        }
        return false;
    }

    private static void getEvenNumbers(List<Integer> numbers) {
        List<Integer> evenNumbers = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                evenNumbers.add(numbers.get(i));
            }
        }
        for (int num : evenNumbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static void getOddNumbers(List<Integer> numbers) {
        List<Integer> oddNumbers = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 != 0) {
                oddNumbers.add(numbers.get(i));
            }
        }
        for (int num : oddNumbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static void getSum(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        System.out.println(sum);
    }

    private static void filtrateToTheNumber(List<Integer> numbers, String condition, int finiteNumber) {
        String output = "";
        switch (condition) {
            case ">":
                for (int num : numbers) {
                    if (num > finiteNumber) {
                        output += num + " ";
                    }
                }
                break;
            case ">=":
                for (int num : numbers) {
                    if (num >= finiteNumber) {
                        output += num + " ";
                    }
                }
                break;
            case "<":
                for (int num : numbers) {
                    if (num < finiteNumber) {
                        output += num + " ";
                    }
                }
                break;
            case "<=":
                for (int num : numbers) {
                    if (num <= finiteNumber) {
                        output += num + " ";
                    }
                }
                break;
        }
        System.out.println(output);
    }
}
