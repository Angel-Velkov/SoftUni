import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CyclesInAGraph {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, List<String>> graph = new HashMap<>();

        String line;

        while (!"End".equals(line = reader.readLine())) {
            String[] edge = line.split("-");

            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        }

        System.out.println("Acyclic: " + (isAcyclic(graph) ? "Yes" : "No"));
    }

    private static boolean flag = true;

    private static boolean isAcyclic(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        Set<String> cycles = new HashSet<>();

        for (String node : graph.keySet()) {
            dfs(node, graph, visited, cycles);
            if (!flag) {
                return false;
            }
        }

        return true;
    }

    private static void dfs(String node, Map<String, List<String>> graph,
                            Set<String> visited, Set<String> cycles) {
        if (cycles.contains(node)) {
            flag = false;
            return;
        }

        if (!visited.contains(node)) {
            visited.add(node);
            cycles.add(node);

            if (graph.get(node) != null) {
                for (String child : graph.get(node)) {
                    dfs(child, graph, visited, cycles);
                }
            }

            cycles.remove(node);
        }
    }
}
