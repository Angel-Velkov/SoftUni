import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BreakCycles {

    private static class Edge {
        String first;
        String second;

        Edge(String first, String second) {
            this.first = first;
            this.second = second;
        }

        public String getFirst() {
            return first;
        }

        public String getSecond() {
            return second;
        }
    }

    private static final Map<String, List<String>> graph = new HashMap<>();
    private static final List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while (!"".equals(line = reader.readLine())) {
            String[] tokens = line.split("\\s+->\\s+");

            String node = tokens[0];
            List<String> children = Arrays.stream(tokens[1].split("\\s+")).collect(Collectors.toList());

            for (String child : children) {
                Edge edge = new Edge(node, child);
                edges.add(edge);
            }

            graph.put(node, children);
        }

        edges.sort(Comparator.comparing(Edge::getFirst).thenComparing(Edge::getSecond));

        List<Edge> removedEdges = removeCyclicEdges();

        System.out.println("Edges to remove: " + removedEdges.size());

        for (Edge edge : removedEdges) {
            System.out.println(edge.getFirst() + " - " + edge.getSecond());
        }
    }

    private static List<Edge> removeCyclicEdges() {
        List<Edge> removedEdges = new ArrayList<>();

        for (Edge edge : edges) {
            boolean removedFirstDirection = graph.get(edge.getFirst()).remove(edge.getSecond());
            boolean removedSecondDirection = graph.get(edge.getSecond()).remove(edge.getFirst());

            if (removedFirstDirection && removedSecondDirection) {
                if (hasAPath(edge.getFirst(), edge.getSecond())) {
                    removedEdges.add(edge);
                } else {
                    graph.get(edge.getFirst()).add(edge.getSecond());
                    graph.get(edge.getSecond()).add(edge.getFirst());
                }
            }
        }
        return removedEdges;
    }

    private static boolean hasAPath(String source, String destination) {
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            String node = queue.poll();

            if (node.equals(destination)) {
                return true;
            }

            if (graph.get(node) != null) {
                for (String child : graph.get(node)) {
                    if (!visited.contains(child)) {
                        queue.offer(child);
                        visited.add(child);
                    }
                }
            }
        }
        return false;
    }
}
