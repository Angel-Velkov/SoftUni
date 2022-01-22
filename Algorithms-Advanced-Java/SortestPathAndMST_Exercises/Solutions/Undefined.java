import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class Undefined {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodesCount = Integer.parseInt(reader.readLine());
        int[][] graph = new int[nodesCount + 1][nodesCount + 1];

        int edgesCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < edgesCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            graph[from][to] = weight;
        }

        int source = Integer.parseInt(reader.readLine());
        int destination = Integer.parseInt(reader.readLine());

        int[] distance = new int[graph.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        int[] previous = new int[graph.length];
        Arrays.fill(previous, -1);

        for (int i = 1; i < graph.length; i++) {
            boolean hasChanged = false;

            for (int row = 1; row < graph.length; row++) {
                for (int col = 1; col < graph[row].length; col++) {

                    int weight = graph[row][col];
                    if (weight != 0 && distance[row] != Integer.MAX_VALUE) {

                        int newWeight = distance[row] + weight;
                        if (newWeight < distance[col]) {
                            distance[col] = newWeight;
                            previous[col] = row;
                            hasChanged = true;

                            if (i == graph.length - 1) {
                                System.out.println("Undefined");
                                return;
                            }
                        }
                    }
                }
            }

            if (!hasChanged) {
                break;
            }
        }

        Deque<Integer> stack = new ArrayDeque<>();

        int node = destination;
        do {
            stack.push(node);
            node = previous[node];
        } while (node != -1);

        System.out.println(
                stack
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );

        System.out.println(distance[destination]);
    }
}
