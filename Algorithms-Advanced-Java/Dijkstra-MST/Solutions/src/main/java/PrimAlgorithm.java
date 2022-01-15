import java.util.*;

public class PrimAlgorithm {

    public static List<Edge> prim(int numberOfVertices, List<Edge> edges) {
        List<Edge> forest = new ArrayList<>();

        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (Edge edge : edges) {
            int firstNode = edge.getStartNode();
            int secondNode = edge.getEndNode();

            graph.computeIfAbsent(firstNode, k -> new ArrayList<>()).add(edge);
            graph.computeIfAbsent(secondNode, k -> new ArrayList<>()).add(edge);
        }

        boolean[] visited = new boolean[numberOfVertices];

        for (int node = 0; node < numberOfVertices; node++) {
            if (!visited[node]) {
                forest.addAll(primTree(node, graph, visited));
            }
        }

        return forest;
    }

    public static List<Edge> primTree(int node, Map<Integer, List<Edge>> graph, boolean[] visited) {
        List<Edge> spanningTree = new ArrayList<>();

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.addAll(graph.get(node));

        int parent = node;
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();

            int firstNode = edge.getStartNode();
            int secondNode = edge.getEndNode();

            if (!visited[firstNode] || !visited[secondNode]) {
                visited[firstNode] = true;
                visited[secondNode] = true;

                spanningTree.add(edge);

                if (parent == firstNode) {
                    queue.addAll(graph.get(secondNode));
                } else {
                    queue.addAll(graph.get(firstNode));
                }
            }

            parent = parent == firstNode ? secondNode : firstNode;
        }

        return spanningTree;
    }
}
