import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CyclesInAGraph {
    private static class Graph {
        Map<String, List<String>> nodes;

        Graph() {
            this.nodes = new HashMap<>();
        }

        void addEdge(String a, String b) {
            this.nodes.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            this.nodes.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }

        Set<String> getNodes() {
            return nodes.keySet();
        }

        List<String> getEdges(String node) {
            return nodes.get(node);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Graph graph = new Graph();

        String line;

        while (!"End".equals(line = reader.readLine())) {
            String[] edge = line.split("-");

            graph.addEdge(edge[0], edge[1]);
        }

        System.out.println("Acyclic: " + (isAcyclic(graph) ? "Yes" : "No"));
    }

    private static boolean isAcyclic(Graph graph) {
        return !isCyclic(graph);
    }

    private static boolean isCyclic(Graph graph) {
        Set<String> visited = new HashSet<>();

        for (String node : graph.getNodes()) {
            if (!visited.contains(node)) {
                if (isCyclicUtil(graph, node, null, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    static Boolean isCyclicUtil(Graph graph, String node, String parent, Set<String> visited) {
        visited.add(node);

        for (String child : graph.getEdges(node)) {
            if (!visited.contains(child)) {
                if (isCyclicUtil(graph, child, node, visited)) {
                    return true;
                }
            } else if (!child.equals(parent)) {
                return true;
            }
        }
        return false;
    }
}
