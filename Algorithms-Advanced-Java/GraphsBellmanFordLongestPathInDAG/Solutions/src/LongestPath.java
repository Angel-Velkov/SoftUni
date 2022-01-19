import java.util.ArrayDeque;
import java.util.Arrays;

public class LongestPath {

    public static int getWeightOfLongestPath(Integer[][] graph, int source, int destination) {
        int[] distance = new int[graph.length];
        Arrays.fill(distance, Integer.MIN_VALUE);
        distance[source] = 0;

        for (int node : topologicalSort(graph, source)) {
            for (int child = 1; child < graph[node].length; child++) {
                Integer weight = graph[node][child];
                if (weight != null) {
                    int newWeight = distance[node] + weight;

                    if (distance[child] < newWeight) {
                        distance[child] = newWeight;
                    }
                }
            }
        }

        return distance[destination];
    }

    private static ArrayDeque<Integer> topologicalSort(Integer[][] graph, int source) {
        ArrayDeque<Integer> sorted = new ArrayDeque<>();

        boolean[] visited = new boolean[graph.length];

        dfs(source, graph, visited, sorted);

        return sorted;
    }

    private static void dfs(int node, Integer[][] graph, boolean[] visited, ArrayDeque<Integer> sorted) {
        if (!visited[node]) {
            visited[node] = true;

            for (int child = 1; child < graph[node].length; child++) {
                if (graph[node][child] != null) {
                    dfs(child, graph, visited, sorted);
                }
            }

            sorted.push(node);
        }
    }
}
