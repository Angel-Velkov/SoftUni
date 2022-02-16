import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RoadTrip {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] value = Arrays.stream(reader.readLine().split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] weight = Arrays.stream(reader.readLine().split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int capacity = Integer.parseInt(reader.readLine());

        int[][] table = new int[value.length + 1][capacity + 1];

        for (int i = 1; i <= value.length; i++) {
            for (int currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {

                int excluded = table[i - 1][currentCapacity];
                if (weight[i - 1] > currentCapacity) {
                    table[i][currentCapacity] = excluded;
                } else {
                    int included = value[i - 1] + table[i - 1][currentCapacity - weight[i - 1]];

                    table[i][currentCapacity] = Math.max(included, excluded);
                }
            }
        }

        System.out.println("Maximum value: " + table[value.length][capacity]);
    }
}
