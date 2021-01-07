package Lab;

import java.util.Scanner;

public class GreaterOfTwoValues {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String type = scanner.nextLine();
        String result = "";
        if (type.equals("int")) {
            int firstNum = Integer.parseInt(scanner.nextLine());
            int secondNum = Integer.parseInt(scanner.nextLine());
            result = getMax(firstNum, secondNum) + "";
        } else if (type.equals("string") || type.equals("char")) {
            String first = scanner.nextLine();
            String second = scanner.nextLine();
            result = getMax(first, second);
        }

        System.out.println(result);
    }

    private static String getMax(String first, String second) {
        if (first.compareTo(second) >= 0) {
            return first;
        }
        return second;
    }

    //TODO: Знам, че този метод не работи. Сложен е за престиж (нищо, че престижът изчезва при конкатенацията на метод със стринг в "int")
    private static char getMax(char first, char second) {
        if (first > second) {
            return first;
        }
        return second;
    }

    private static int getMax(int first, int second) {
        if (first > second) {
            return first;
        }
        return second;
    }
}
