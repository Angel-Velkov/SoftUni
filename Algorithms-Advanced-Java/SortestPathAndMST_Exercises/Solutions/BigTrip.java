import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BigTrip {

    private static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodesCount = Integer.parseInt(reader.readLine());
        Map<Integer, List<Edge>> graph = new HashMap<>(nodesCount);
        for (int i = 1; i <= nodesCount; i++) {
            graph.put(i, new ArrayList<>());
        }

        int edgesCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < edgesCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            Edge edge = new Edge(from, to, weight);
            graph.get(from).add(edge);
        }

        int source = Integer.parseInt(reader.readLine());
        int destination = Integer.parseInt(reader.readLine());

        Map<Integer, Integer> distance = new HashMap<>();
        for (int node : graph.keySet()) {
            distance.put(node, Integer.MIN_VALUE);
        }
        distance.put(source, 0);

        Map<Integer, Integer> previous = new HashMap<>();

        Deque<Integer> sored = topologicalSort(graph, source);

        for (int node : sored) {
            for (Edge edge : graph.get(node)) {
                int child = edge.to;

                int newWeight = distance.get(node) + edge.weight;
                if (newWeight > distance.get(child)) {
                    distance.put(child, newWeight);
                    previous.put(child, node);
                }
            }
        }

        Deque<Integer> path = new ArrayDeque<>();

        Integer node = destination;
        do {
            path.push(node);
            node = previous.get(node);
        } while (node != null);

        System.out.println(distance.get(destination));
        System.out.println(
                path
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }

    private static Deque<Integer> topologicalSort(Map<Integer, List<Edge>> graph, int staringNode) {
        Deque<Integer> sorted = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        bfs(staringNode, graph, visited, sorted);

        return sorted;
    }

    private static void bfs(int node, Map<Integer, List<Edge>> graph, Set<Integer> visited, Deque<Integer> sorted) {
        if (!visited.contains(node)) {
            visited.add(node);

            for (Edge edge : graph.get(node)) {
                bfs(edge.to, graph, visited, sorted);
            }

            sorted.push(node);
        }
    }
}
