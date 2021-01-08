package Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PokemonDontGo2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> pokemonDistance = parseLineOfNumbers(scanner);
        int sumOfRemovedNumbers = 0;

        while (!pokemonDistance.isEmpty()) {
            int index = Integer.parseInt(scanner.nextLine());
            if (index < 0) {
                pokemonDistance.add(1, pokemonDistance.get(pokemonDistance.size() - 1));
                index = 0;
            } else if (index > pokemonDistance.size() - 1) {
                pokemonDistance.add(pokemonDistance.size() - 1, pokemonDistance.get(0));
                index = pokemonDistance.size() - 1;
            }

            int removedElement = pokemonDistance.remove(index);
            sumOfRemovedNumbers += removedElement;
            for (int i = 0; i < pokemonDistance.size(); i++) {

                int currentElement = pokemonDistance.get(i);

                if (removedElement >= currentElement) {
                    pokemonDistance.set(i, currentElement + removedElement);
                } else {
                    pokemonDistance.set(i, currentElement - removedElement);
                }
            }
        }
        System.out.println(sumOfRemovedNumbers);
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
