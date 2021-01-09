package November2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;

public class AngryPet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> priceRatings = parseLineOfNumbers(scanner);

        int entryPoint = Integer.parseInt(scanner.nextLine());
        String itemsType = scanner.nextLine();
        String typeOfPriceRatings = scanner.nextLine();

        int leftSum = 0;
        int rightSum = 0;

        for (int i = 0; i < entryPoint; i++) {
            if (itemsType.equals("expensive") && priceRatings.get(entryPoint) <= priceRatings.get(i)) {
                if (typeOfPriceRatings.equals("positive") && priceRatings.get(i) > 0) {
                    leftSum += priceRatings.get(i);
                } else if (typeOfPriceRatings.equals("negative") && priceRatings.get(i) < 0) {
                    leftSum += priceRatings.get(i);
                } else if (typeOfPriceRatings.equals("all")) {
                    leftSum += priceRatings.get(i);
                }
            } else if (itemsType.equals("cheap") && priceRatings.get(entryPoint) > priceRatings.get(i)) {
                if (typeOfPriceRatings.equals("positive") && priceRatings.get(i) > 0) {
                    leftSum += priceRatings.get(i);
                } else if (typeOfPriceRatings.equals("negative") && priceRatings.get(i) < 0) {
                    leftSum += priceRatings.get(i);
                } else if (typeOfPriceRatings.equals("all")) {
                    leftSum += priceRatings.get(i);
                }
            }
        }

        for (int i = entryPoint + 1; i < priceRatings.size(); i++) {
            if (itemsType.equals("expensive") && priceRatings.get(entryPoint) <= priceRatings.get(i)) {

                if (typeOfPriceRatings.equals("positive") && priceRatings.get(i) > 0) {
                    rightSum += priceRatings.get(i);

                } else if (typeOfPriceRatings.equals("negative") && priceRatings.get(i) < 0) {
                    rightSum += priceRatings.get(i);

                } else if (typeOfPriceRatings.equals("all")) {
                    rightSum += priceRatings.get(i);
                }
            } else if (itemsType.equals("cheap") && priceRatings.get(entryPoint) > priceRatings.get(i)) {

                if (typeOfPriceRatings.equals("positive") && priceRatings.get(i) > 0) {
                    rightSum += priceRatings.get(i);

                } else if (typeOfPriceRatings.equals("negative") && priceRatings.get(i) < 0) {
                    rightSum += priceRatings.get(i);

                } else if (typeOfPriceRatings.equals("all")) {
                    rightSum += priceRatings.get(i);
                }
            }
        }

        if (leftSum >= rightSum) {
            System.out.println("Left - " + leftSum);
        } else {
            System.out.println("Right - " + rightSum);
        }

    }

    private static List<Integer> parseLineOfNumbers(Scanner scanner) {
        String[] numbersAsString = scanner.nextLine().split(" ");
        List<Integer> numbers = new ArrayList<>();

        for (String s : numbersAsString) {
            int num = Integer.parseInt(s);
            numbers.add(num);
        }

        return numbers;
    }
}

