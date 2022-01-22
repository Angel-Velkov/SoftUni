import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class CheapTownTour {

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

        int nodesCount = Integer.parseInt(reader.readLine());
        int edgesCount = Integer.parseInt(reader.readLine());

        PriorityQueue<Edge> queue = new PriorityQueue<>();

        for (int i = 0; i < edgesCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s*-\\s*"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            Edge edge = new Edge(from, to, weight);

            queue.offer(edge);
        }

        int[] parent = new int[nodesCount];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        int totalCost = 0;

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();

            int firstRoot = findRoot(edge.from, parent);
            int secondRoot = findRoot(edge.to, parent);

            if (firstRoot != secondRoot) {
                totalCost += edge.weight;
                parent[firstRoot] = secondRoot;
            }
        }

        System.out.println("Total cost: " + totalCost);
    }

    private static int findRoot(int node, int[] parent) {
        int root = node;

        while (root != parent[root]) {
            root = parent[root];
        }

        return root;
    }
}
