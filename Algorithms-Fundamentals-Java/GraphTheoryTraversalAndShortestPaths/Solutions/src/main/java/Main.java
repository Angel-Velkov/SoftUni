import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        List<List<Integer>> graph = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            List<Integer> nodeChildren = Arrays.stream(reader.readLine().split("\\s+"))
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            graph.add(nodeChildren);
        }

        for (Deque<Integer> connectedComponent : getConnectedComponents(graph)) {
            System.out.println("Connected component: " +
                    connectedComponent.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }

    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];

        List<Deque<Integer>> components = new ArrayList<>();

        for (int startingPoint = 0; startingPoint < graph.size(); startingPoint++) {
            if (!visited[startingPoint]) {
                components.add(new ArrayDeque<>());
                dfs(startingPoint, graph, visited, components);
            }
        }

        return components;
    }

    private static void dfs(int node, List<List<Integer>> graph, boolean[] visited, List<Deque<Integer>> components) {
        if (!visited[node]) {
            visited[node] = true;

            for (Integer child : graph.get(node)) {
                dfs(child, graph, visited, components);
            }

            components.get(components.size() - 1).offer(node);
        }
    }

    public static Collection<String> topSort(Map<String, List<String>> graph) {
        throw new AssertionError("Not Implemented");
    }
}
