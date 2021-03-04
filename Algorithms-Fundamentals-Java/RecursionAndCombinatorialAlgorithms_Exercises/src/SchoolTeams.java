import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SchoolTeams {
    private static String[] girls;
    private static final String[] girlsCombinations = new String[3];
    private static final List<String> allGirlsCombinations = new ArrayList<>();

    private static String[] boys;
    private static final String[] boysCombinations = new String[2];
    private static final List<String> allBoysCombinations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        girls = reader.readLine().split(",\\s*");
        boys = reader.readLine().split(",\\s*");

        combineGirls(0, 0);
        combineBoys(0, 0);

        for (String girlsComb : allGirlsCombinations) {
            for (String boysComb : allBoysCombinations) {
                System.out.println(girlsComb + ", " + boysComb);
            }
        }
    }

    private static void combineGirls(int index, int start) {
        if (index >= girlsCombinations.length) {
            allGirlsCombinations.add(String.join(", ", girlsCombinations));
        } else {
            for (int i = start; i < girls.length; i++) {
                girlsCombinations[index] = girls[i];
                combineGirls(index + 1, i + 1);
            }
        }
    }

    private static void combineBoys(int index, int start) {
        if (index >= boysCombinations.length) {
            allBoysCombinations.add(String.join(", ", boysCombinations));
        } else {
            for (int i = start; i < boys.length; i++) {
                boysCombinations[index] = boys[i];
                combineBoys(index + 1, i + 1);
            }
        }
    }
}
