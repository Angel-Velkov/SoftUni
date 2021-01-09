package Lab;

import java.util.*;

public class WordSynonym {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> wordSynonyms = new LinkedHashMap<>();

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {

            String word = scanner.nextLine();
            String synonym = scanner.nextLine();

            List<String> currentSynonym = wordSynonyms.get(word);
            if (currentSynonym == null) {
                currentSynonym = new ArrayList<>();
                wordSynonyms.put(word, currentSynonym);
            }
            currentSynonym.add(synonym);
        }

        for (Map.Entry<String, List<String>> entry : wordSynonyms.entrySet()) {
            System.out.println(entry.getKey() + " - " + String.join(", ", entry.getValue()));
        }
    }
}
