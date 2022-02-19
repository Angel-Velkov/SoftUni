import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class DataTransfer {
    private static int[][] graph;
    private static int[] prev;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        graph = new int[nodes][nodes];

        int edges = Integer.parseInt(reader.readLine());

        int[] goals = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int source = goals[0];
        int destination = goals[1];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[tokens[0]][tokens[1]] = tokens[2];
        }

        int maxFlow = 0;

        prev = new int[nodes];

        while (bfs(source, destination)) {
            int flow = Integer.MAX_VALUE;

            int node = destination;
            while (node != source) {
                int parent = prev[node];
                flow = Math.min(flow, graph[parent][node]);
                node = prev[node];
            }

            maxFlow += flow;

            node = destination;
            while (node != source) {
                int parent = prev[node];
                graph[parent][node] -= flow;
                graph[node][parent] += flow;
                node = prev[node];
            }
        }

        System.out.println(maxFlow);
    }

    private static boolean bfs(int source, int destination) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[graph.length];

        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == destination) {
                break;
            }

            for (int child = 0; child < graph.length; child++) {
                if (graph[node][child] > 0 && !visited[child]) {
                    queue.offer(child);
                    visited[child] = true;
                    prev[child] = node;
                }
            }
        }

        return visited[destination];
    }
}
