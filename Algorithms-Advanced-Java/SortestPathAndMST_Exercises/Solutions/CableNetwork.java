import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CableNetwork {

    private static class Edge implements Comparable<Edge> {
        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }

        @Override
        public String toString() {
            return this.from + " -> " + this.to + ": " + this.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int budget = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int nodesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int edgesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < nodesCount; i++) {
            graph.put(i, new ArrayList<>());
        }

        int root = -1;
        Set<Integer> fixedNetwork = new HashSet<>();

        for (int i = 0; i < edgesCount; i++) {
            String[] tokens = reader.readLine().split("\\s+");

            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);

            if (tokens.length > 3) {
                if (root == -1) {
                    root = from;
                }
                fixedNetwork.add(from);
                fixedNetwork.add(to);
            } else {
                Edge edge = new Edge(from, to, weight);
                graph.get(from).add(edge);
                graph.get(to).add(edge);
            }
        }

        if (root == -1) {
            throw new IllegalArgumentException("The graph does not has a root");
        }

        mergeAllConnectedNodesIntoOneRoot(root, fixedNetwork, graph);

        System.out.println("Budget used: " + prim(graph, root, budget));
    }

    private static void mergeAllConnectedNodesIntoOneRoot(int mainNode, Set<Integer> fixedNetwork,
                                                          Map<Integer, List<Edge>> graph) {

        Map<Integer, Edge> incomingNodes = new HashMap<>();

        for (Integer node : fixedNetwork) {
            Iterator<Edge> iterator = graph.get(node).iterator();

            while (iterator.hasNext()) {
                Edge currentEdge = iterator.next();

                if (fixedNetwork.contains(currentEdge.from) && fixedNetwork.contains(currentEdge.to)) {
                    iterator.remove();
                } else {
                    if (currentEdge.from == node) {
                        currentEdge.from = mainNode;

                        if (incomingNodes.containsKey(currentEdge.to)) {
                            if (incomingNodes.get(currentEdge.to).weight > currentEdge.weight) {

                                incomingNodes.put(currentEdge.to, currentEdge);
                            }
                        } else {
                            incomingNodes.put(currentEdge.to, currentEdge);
                        }
                    } else if (currentEdge.to == node) {
                        currentEdge.to = mainNode;

                        if (incomingNodes.containsKey(currentEdge.from)) {
                            if (incomingNodes.get(currentEdge.from).weight > currentEdge.weight) {
                                incomingNodes.put(currentEdge.from, currentEdge);
                            }
                        } else {
                            incomingNodes.put(currentEdge.from, currentEdge);
                        }
                    }
                }
            }
        }

        for (Integer node : fixedNetwork) {
            graph.remove(node);
        }

        graph.put(mainNode, new ArrayList<>());
        for (Edge edge : incomingNodes.values()) {
            graph.get(mainNode).add(edge);
        }
    }

    private static int prim(Map<Integer, List<Edge>> graph, int root, int maxWeight) {
        int totalWeight = 0;

        Set<Integer> visited = new HashSet<>();
        visited.add(root);

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.addAll(graph.get(root));

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();

            int firstNode = edge.from;
            int secondNode = edge.to;

            if (totalWeight + edge.weight < maxWeight) {
                if (!visited.contains(firstNode)) {
                    visited.add(firstNode);
                    totalWeight += edge.weight;
                    offerUnvisitedChildren(queue, graph, firstNode, visited);
                } else if (!visited.contains(secondNode)) {
                    visited.add(secondNode);
                    totalWeight += edge.weight;
                    offerUnvisitedChildren(queue, graph, secondNode, visited);
                }
            } else {
                break;
            }
        }

        return totalWeight;
    }

    private static void offerUnvisitedChildren(PriorityQueue<Edge> queue, Map<Integer, List<Edge>> graph,
                                               int node, Set<Integer> visited) {

        graph.get(node)
                .stream()
                .filter(e -> !visited.contains(e.from) || !visited.contains(e.to))
                .forEach(queue::offer);
    }
}
