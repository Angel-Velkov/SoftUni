import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ClusterBorder {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] singleShipTime = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] pairOfShipsTime = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] table = new int[singleShipTime.length + 1];
        table[1] = singleShipTime[0];

        for (int i = 2; i < table.length; i++) {
            table[i] = Math.min(table[i - 1] + singleShipTime[i - 1], table[i - 2] + pairOfShipsTime[i - 2]);
        }

        System.out.println("Optimal Time: " + table[singleShipTime.length]);

        List<String> result = new ArrayList<>();
        for (int i = table.length - 1; i > 0; i--) {
            if (table[i] - table[i - 1] == singleShipTime[i - 1]) {
                result.add("Single " + i);
            } else {
                result.add("Pair of " + (i - 1) + " and " + i);
                i--;
            }
        }

        Collections.reverse(result);
        result.forEach(System.out::println);
    }
}
