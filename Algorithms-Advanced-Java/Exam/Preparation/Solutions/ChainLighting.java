import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ChainLighting {
    private static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

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

    private static int mostDamaged = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodesCount = Integer.parseInt(reader.readLine());
        int edgesCount = Integer.parseInt(reader.readLine());
        int lightningsCount = Integer.parseInt(reader.readLine());

        PriorityQueue<Edge> edges = new PriorityQueue<>();

        for (int i = 0; i < edgesCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            edges.add(new Edge(from, to, weight));
        }

        List<Integer>[] msf = findMinimumSpanningForest(nodesCount, edges);
        int[] takenDamage = new int[nodesCount];

        for (int i = 0; i < lightningsCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int node = tokens[0];
            int strikeDamage = tokens[1];

            lightningStrike(node, msf, strikeDamage, takenDamage);
        }

        System.out.println(mostDamaged);
    }

    private static void lightningStrike(int node, List<Integer>[] msf, int strikeDamage, int[] takenDamage) {
        boolean[] visited = new boolean[msf.length];

        dfs(node, msf, strikeDamage, takenDamage, visited);
    }

    private static void dfs(int node, List<Integer>[] msf, int strikeDamage, int[] takenDamage, boolean[] visited) {
        if (!visited[node] && strikeDamage != 0) {
            visited[node] = true;

            takenDamage[node] += strikeDamage;
            if (takenDamage[node] > mostDamaged) {
                mostDamaged = takenDamage[node];
            }

            for (int child : msf[node]) {
                dfs(child, msf, strikeDamage / 2, takenDamage, visited);
            }
        }
    }

    private static List<Integer>[] findMinimumSpanningForest(int nodesCount, PriorityQueue<Edge> edges) {
        List<Integer>[] forest = new List[nodesCount];
        int[] parents = new int[nodesCount];

        for (int node = 0; node < nodesCount; node++) {
            forest[node] = new ArrayList<>();
            parents[node] = node;
        }

        while (!edges.isEmpty()) {
            Edge edge = edges.poll();

            int firstRoot = findRoot(edge.from, parents);
            int secondRoot = findRoot(edge.to, parents);

            if (firstRoot != secondRoot) {
                forest[edge.from].add(edge.to);
                forest[edge.to].add(edge.from);

                parents[firstRoot] = secondRoot;
            }
        }

        return forest;
    }

    private static int findRoot(int node, int[] parents) {
        while (node != parents[node]) {
            node = parents[node];
        }

        return node;
    }
}