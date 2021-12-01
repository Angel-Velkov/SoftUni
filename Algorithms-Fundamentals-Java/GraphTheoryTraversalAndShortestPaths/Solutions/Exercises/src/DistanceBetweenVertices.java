import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class DistanceBetweenVertices {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int p = Integer.parseInt(reader.readLine());

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(":");

            int node = Integer.parseInt(tokens[0]);

            List<Integer> children;
            if (tokens.length != 1) {
                children = Arrays.stream(tokens[1].split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
            } else {
                children = new ArrayList<>();
            }

            graph.put(node, children);
        }

        for (int i = 0; i < p; i++) {
            int[] pair = Arrays.stream(reader.readLine().split("-"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int source = pair[0];
            int destination = pair[1];

            int steps = getNumberOfSteps(graph, source, destination);

            System.out.printf("{%s, %s} -> %d%n",
                    source, destination, steps);
        }
    }

    private static int getNumberOfSteps(Map<Integer, List<Integer>> graph, int source, int destination) {
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> levels = new HashMap<>();

        queue.offer(source);
        visited.add(source);
        levels.put(source, 0);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == destination) {
                return levels.get(destination);
            }

            for (Integer child : graph.get(node)) {
                if (!visited.contains(child)) {
                    queue.offer(child);
                    visited.add(child);
                    levels.put(child, levels.get(node) + 1);
                }
            }
        }

        return -1;
    }
}
