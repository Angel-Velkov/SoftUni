import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class TheStoryTelling {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, List<String>> graph = new LinkedHashMap<>();

        String line;
        while (!"End".equals(line = reader.readLine())) {
            String[] tokens = line.split("\\s*->\\s*");

            String node = tokens[0];

            graph.putIfAbsent(node, new ArrayList<>());

            if (tokens.length > 1) {
                List<String> children = Arrays.stream(tokens[1].split("\\s+"))
                        .collect(Collectors.toList());

                for (String child : children) {
                    graph.putIfAbsent(child, new ArrayList<>());
                    graph.get(node).add(child);
                }
            }
        }

        System.out.println(
                String.join(" ", topologicalRemoval(graph))
        );
    }

    private static List<String> topologicalRemoval(Map<String, List<String>> graph) {
        Map<String, Integer> dependenciesCount = getDependenciesCount(graph);

        List<String> removedNodes = new ArrayList<>();

        while (!graph.isEmpty()) {
            String nodeToRemove = graph.keySet()
                    .stream()
                    .filter(node -> dependenciesCount.get(node) == 0)
                    .reduce((first, second) -> second)
                    .orElse(null);

            if (nodeToRemove == null) {
                break;
            }

            for (String child : graph.get(nodeToRemove)) {
                dependenciesCount.put(child, dependenciesCount.get(child) - 1);
            }

            removedNodes.add(nodeToRemove);
            graph.remove(nodeToRemove);
        }

        return removedNodes;
    }

    private static Map<String, Integer> getDependenciesCount(Map<String, List<String>> graph) {
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
}
