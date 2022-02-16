import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TourDeSofia {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        List<Integer>[] graph = new List[nodes];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        int edges = Integer.parseInt(reader.readLine());
        int source = Integer.parseInt(reader.readLine());

        for (int i = 0; i < edges; i++) {
            int[] edge = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[edge[0]].add(edge[1]);
        }

        System.out.println(findShortestPath(graph, source, source));
    }

    private static int findShortestPath(List<Integer>[] graph, int source, int destination) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[graph.length];
        int[] steps = new int[graph.length];

        queue.offer(source);

        int nodesCount = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            nodesCount++;

            for (int child : graph[node]) {
                if (!visited[child]) {
                    queue.offer(child);
                    visited[child] = true;
                    steps[child] = steps[node] + 1;

                    if (child == destination) {
                        return steps[child];
                    }
                }
            }
        }

        return nodesCount;
    }
}
