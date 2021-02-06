package Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PokemonDontGo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> distanceFromPokemons = parseLineOfNumbers(scanner);
        int sumOfRemovedNumbers = 0;

        // TODO: Всеки елемент който махаме трябва да бъде причисляван в ELSE-а.
        // TODO: Първите две проверки махат елементи (първата маха числото на първия индекс а втората числото на последния.

        while (!distanceFromPokemons.isEmpty()) {
            int index = Integer.parseInt(scanner.nextLine());
            if (index < 0) {
                sumOfRemovedNumbers += distanceFromPokemons.get(0);
                distanceFromPokemons.set(0, distanceFromPokemons.get(distanceFromPokemons.size() - 1));

            } else if (index > distanceFromPokemons.size() - 1) {
                sumOfRemovedNumbers += distanceFromPokemons.get(distanceFromPokemons.size() - 1);
                distanceFromPokemons.set(distanceFromPokemons.size() - 1, distanceFromPokemons.get(0));

            } else {
                int removedElement = distanceFromPokemons.remove(index);
                sumOfRemovedNumbers += removedElement;
                for (int i = 0; i < distanceFromPokemons.size(); i++) {
                    int currentElement = distanceFromPokemons.get(i);

                    if (removedElement >= currentElement) {
                        distanceFromPokemons.set(i, currentElement + removedElement);
                    } else {
                        distanceFromPokemons.set(i, currentElement - removedElement);
                    }
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
