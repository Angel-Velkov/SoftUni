import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class TravelOptimizer {
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

        Map<Integer, List<Edge>> graph = new HashMap<>();

        int edges = Integer.parseInt(reader.readLine());
        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split(",\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph.computeIfAbsent(tokens[0], k -> new ArrayList<>()).add(new Edge(tokens[0], tokens[1], tokens[2]));
            graph.computeIfAbsent(tokens[1], k -> new ArrayList<>()).add(new Edge(tokens[1], tokens[0], tokens[2]));
        }

        int source = Integer.parseInt(reader.readLine());
        int destination = Integer.parseInt(reader.readLine());
        int stops = Integer.parseInt(reader.readLine());

        List<Integer> shortestPath = findShortestPath(graph, source, destination, stops);

        if (!shortestPath.isEmpty()) {
            System.out.println(
                    shortestPath
                            .stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(" "))
            );
        } else {
            System.out.println("There is no such path.");
        }
    }

    private static List<Integer> findShortestPath(Map<Integer, List<Edge>> graph, int source, int destination, int stops) {
        Map<Integer, Integer> prev = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparing(i -> i[0]));
        queue.offer(new int[]{0, source, stops});

        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();

            int cost = tuple[0];
            int node = tuple[1];
            int stop = tuple[2];

            if (node == destination) {
                break;
            }

            visited.add(node);

            if (stop >= 0 && graph.get(node) != null) {
                for (Edge edge : graph.get(node)) {
                    int child = edge.to;
                    if (!visited.contains(child)) {
                        queue.offer(new int[]{cost + edge.weight, child, stop - 1});
                        prev.put(child, node);
                    }
                }
            }
        }

        return getPath(prev, destination);
    }

    private static List<Integer> getPath(Map<Integer, Integer> prev, int destination) {
        List<Integer> path = new ArrayList<>();

        Integer node = destination;

        do {
            path.add(node);
            node = prev.get(node);
        } while (node != null);

        if (path.size() == 1) {
            return Collections.emptyList();
        }

        Collections.reverse(path);

        return path;
    }
}
