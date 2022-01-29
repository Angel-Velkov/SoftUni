import java.util.ArrayDeque;
import java.util.Queue;

public class EdmondsKarp {
    private static int[][] graph;

    public static int findMaxFlow(int[][] targetGraph) {
        graph = targetGraph;

        int maxFlow = 0;

        int[] prev = new int[graph.length];
        while (dfs(prev)) {
            int flow = Integer.MAX_VALUE;

            int node = graph.length - 1;
            while (node != 0) {
                int parent = prev[node];
                flow = Math.min(flow, graph[parent][node]);
                node = prev[node];
            }

            maxFlow += flow;

            node = graph.length - 1;
            while (node != 0) {
                int parent = prev[node];
                graph[parent][node] -= flow;
                node = prev[node];
            }
        }

        return maxFlow;
    }

    private static boolean dfs(int[] prev) {
        int source = 0;
        int destination = graph.length - 1;

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[graph.length];

        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int child = 0; child < graph[node].length; child++) {
                int flow = graph[node][child];

                if (!visited[child] && flow > 0) {
                    visited[child] = true;
                    queue.offer(child);
                    prev[child] = node;
                }
            }
        }

        return visited[destination];
    }
}
