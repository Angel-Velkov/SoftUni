package MoreExercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MixedUpLists {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> firstList = parseLineOfNumbers(scanner);
        List<Integer> secondList = parseLineOfNumbers(scanner);

        List<Integer> mixed = new ArrayList<>();
        int smallerLength = Math.min(firstList.size(), secondList.size());
        for (int i = 0; i < smallerLength; i++) {
            mixed.add(firstList.remove(0));
            mixed.add(secondList.remove(secondList.size() - 1));
        }

        int a;
        int b;
        if (!firstList.isEmpty()) {
            a = Math.min(firstList.get(0), firstList.get(1));
            b = Math.max(firstList.get(0), firstList.get(1));
        } else {
            a = Math.min(secondList.get(0), secondList.get(1));
            b = Math.min(secondList.get(0), secondList.get(1));
        }

        int i = 0;
        while (i < mixed.size()) {
            if (a >= mixed.get(i) || b <= mixed.get(i)) {
                mixed.remove(mixed.get(i));
            } else {
                i++;
            }
        }

        Collections.sort(mixed);
        for (Integer integer : mixed) {
            System.out.print(integer + " ");
        }
    }

    private static List<Integer> parseLineOfNumbers(Scanner scanner) {
        List<Integer> numbers = new ArrayList<>();
        String[] numbersAsString = scanner.nextLine().split("\\s+");

        for (String s : numbersAsString) {
            int num = Integer.parseInt(s);
            numbers.add(num);
        }
        return numbers;
    }
}
