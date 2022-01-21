import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ModifiedKruskalAlgorithm {
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
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int edgesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < edgesCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            Edge edge = new Edge(from, to, weight);
            edges.add(edge);
        }

        LinkedList<Edge> soredEdges = new LinkedList<>(edges);
        Collections.sort(soredEdges);

        int forestWeight = 0;

        int[] parent = new int[nodesCount];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        while (!soredEdges.isEmpty()) {
            Edge edge = soredEdges.pollFirst();

            int firstRoot = findRoot(edge.from, parent);
            int secondRoot = findRoot(edge.to, parent);

            if (firstRoot != secondRoot) {
                forestWeight += edge.weight;
                parent[firstRoot] = secondRoot;
            }
        }

        System.out.println("Minimum spanning forest weight: " + forestWeight);
    }

    private static int findRoot(int node, int[] parent) {
        int root = node;
        while (root != parent[root]) {
            root = parent[root];
        }

        while (node != root) {
            int oldParent = parent[node];
            parent[node] = root;
            node = oldParent;
        }

        return root;
    }
}
