import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class WordCruncher {
    private static final Map<Integer, List<String>> syllablesOnIndex = new HashMap<>();
    private static String target;
    private static final List<String> wordCombinations = new ArrayList<>();
    private static final Map<String, Integer> occurrences = new HashMap<>();
    private static final Set<String> output = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> substrings = Arrays.stream(reader.readLine().split(",\\s*"))
                .collect(Collectors.toList());

        target = reader.readLine();
        substrings.removeIf(s -> !target.contains(s));

        for (String substr : substrings) {
            occurrences.putIfAbsent(substr, 0);
            occurrences.put(substr, occurrences.get(substr) + 1);

            int index = target.indexOf(substr);
            while (index > -1) {

                syllablesOnIndex.putIfAbsent(index, new ArrayList<>());
                syllablesOnIndex.get(index).add(substr);
                index = target.indexOf(substr, index + 1);
            }
        }

        permute(0);
        for (String str : output) {
            System.out.println(str);
        }
    }

    private static void permute(int index) {
        if (index == target.length()) {
            output.add(parseString(wordCombinations, " "));
        } else if (syllablesOnIndex.containsKey(index)) {
            List<String> syllable = syllablesOnIndex.get(index);
            for (String str : syllable) {
                if (occurrences.get(str) > 0) {
                    occurrences.put(str, occurrences.get(str) - 1);
                    wordCombinations.add(str);
                    permute(index + str.length());
                    wordCombinations.remove(wordCombinations.size() - 1);
                    occurrences.put(str, occurrences.get(str) + 1);
                }
            }
        }
    }

    private static String parseString(List<String> list, String delimiter) {
        return String.join(delimiter, list);
    }
}
