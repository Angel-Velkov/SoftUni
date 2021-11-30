import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];

        List<Deque<Integer>> components = new ArrayList<>();

        for (int startingPoint = 0; startingPoint < graph.size(); startingPoint++) {
            if (!visited[startingPoint]) {
                Deque<Integer> component = new ArrayDeque<>();
                components.add(component);
                // dfs/bfs
                dfs(startingPoint, graph, visited, component);
            }
        }

        return components;
    }

    private static void dfs(int node, List<List<Integer>> graph, boolean[] visited, Deque<Integer> component) {
        if (!visited[node]) {
            visited[node] = true;

            for (Integer child : graph.get(node)) {
                dfs(child, graph, visited, component);
            }

            component.offer(node);
        }
    }

    private static void bfs(int node, List<List<Integer>> graph, boolean[] visited, Deque<Integer> component) {
        if (!visited[node]) {
            Queue<Integer> queue = new ArrayDeque<>();

            queue.offer(node);
            visited[node] = true;

            while (!queue.isEmpty()) {
                node = queue.poll();
                component.offer(node);

                for (Integer child : graph.get(node)) {
                    if (!visited[child]) {
                        queue.offer(child);
                        visited[child] = true;
                    }
                }
            }
        }
    }

    // DFS Topological Sorting
    public static Collection<String> topSort(Map<String, List<String>> graph) {
        List<String> sorted = new ArrayList<>();

        Set<String> visited = new HashSet<>();
        Set<String> cycles = new HashSet<>();

        for (String node : graph.keySet()) {
            dfs(node, graph, visited, sorted, cycles);
        }

        Collections.reverse(sorted);

        return sorted;
    }

    private static void dfs(String node, Map<String, List<String>> graph,
                            Set<String> visited, List<String> sorted, Set<String> cycles) {
        if (cycles.contains(node)) {
            throw new IllegalArgumentException();
        }

        if (!visited.contains(node)) {
            visited.add(node);
            cycles.add(node);

            for (String child : graph.get(node)) {
                dfs(child, graph, visited, sorted, cycles);
            }

            cycles.remove(node);

            sorted.add(node);
        }
    }

    /*
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

                dependenciesCount.put(child, count == null ? 1 : count + 1);
            }
        }

        return dependenciesCount;
    }
    */
}
