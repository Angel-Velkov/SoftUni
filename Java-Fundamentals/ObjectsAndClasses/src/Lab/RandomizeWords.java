package Lab;

import java.util.*;

public class RandomizeWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] word = scanner.nextLine().split("\\s+");

        Random randomGenerator = new Random();
        for (int i = 0; i < word.length; i++) {
            int x = randomGenerator.nextInt(word.length);
            int y = randomGenerator.nextInt(word.length);

            String oldWordX = word[x];
            word[x] = word[y];
            word[y] = oldWordX;
        }

        for (String s : word) {
            System.out.println(s);
        }
    }
}
