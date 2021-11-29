import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Test for 'Connected Components'
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
        Map<String, Integer> dependenciesCount = gerDependenciesCount(graph);

        List<String> sorted = new ArrayList<>();

        while (!graph.isEmpty()) {
            String nodeToRemove = graph.keySet()
                    .stream()
                    .filter(k -> dependenciesCount.get(k) == 0)
                    .findFirst()
                    .orElse(null);

            if (nodeToRemove == null) {
                break;
            }

            for (String child : graph.get(nodeToRemove)) {
                dependenciesCount.put(child, dependenciesCount.get(child) - 1);
            }

            graph.remove(nodeToRemove);
            sorted.add(nodeToRemove);
        }

        if (!graph.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return sorted;
    }

    private static Map<String, Integer> gerDependenciesCount(Map<String, List<String>> graph) {
        Map<String, Integer> dependenciesCount = new HashMap<>();

        for (Map.Entry<String, List<String>> node : graph.entrySet()) {
            dependenciesCount.putIfAbsent(node.getKey(), 0);

            for (String child : node.getValue()) {
                Integer count = dependenciesCount.get(child);

                if (count == null) {
                    count = 0;
                }

                dependenciesCount.put(child, count + 1);
            }
        }

        return dependenciesCount;
    }
}
