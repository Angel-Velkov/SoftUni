import java.util.*;

public class PrimAlgorithm {

    public static List<Edge> prim(int numberOfVertices, List<Edge> edges) {
        List<Edge> spanningForest = new ArrayList<>();

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
                visited[node] = true;
                spanningForest.addAll(primTree(node, graph, visited));
            }
        }

        return spanningForest;
    }

    public static List<Edge> primTree(int node, Map<Integer, List<Edge>> graph, boolean[] visited) {
        List<Edge> spanningTree = new ArrayList<>();

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.addAll(graph.get(node));

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();

            int firstNode = edge.getStartNode();
            int secondNode = edge.getEndNode();

            if (!visited[firstNode]) {
                visited[firstNode] = true;
                spanningTree.add(edge);
                offerTheUnvisitedChildren(queue, graph, firstNode, visited);
            } else if (!visited[secondNode]) {
                visited[secondNode] = true;
                spanningTree.add(edge);
                offerTheUnvisitedChildren(queue, graph, secondNode, visited);
            }
        }

        return spanningTree;
    }

    private static void offerTheUnvisitedChildren(PriorityQueue<Edge> queue, Map<Integer,
            List<Edge>> graph, int node, boolean[] visited) {

        graph.get(node)
                .stream()
                .filter(e -> !visited[e.getStartNode()] || !visited[e.getEndNode()])
                .forEach(queue::add);
    }
}