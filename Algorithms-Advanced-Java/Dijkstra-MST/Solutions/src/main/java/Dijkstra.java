import java.util.*;

public class Dijkstra {

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {

        int[] distances = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        int[] previous = new int[graph.length];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[sourceNode] = 0;
        Arrays.fill(previous, -1);

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node]));
        queue.offer(sourceNode);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = true;

            if (node == destinationNode) {
                break;
            }

            for (int childNode = 0; childNode < graph[node].length; childNode++) {
                int edgeWeight = graph[node][childNode];

                if (edgeWeight != 0 && !visited[childNode]) {
                    queue.offer(childNode);

                    int newDistance = distances[node] + edgeWeight;

                    if (newDistance < distances[childNode]) {
                        distances[childNode] = newDistance;
                        previous[childNode] = node;
                    }
                }
            }
        }

        return getPath(destinationNode, previous);
    }

    private static List<Integer> getPath(int destinationNode, int[] previous) {
        if (previous[destinationNode] == -1) {
            return null;
        }

        List<Integer> path = new ArrayList<>();

        int node = destinationNode;

        while (node != -1) {
            path.add(node);
            node = previous[node];
        }

        Collections.reverse(path);
        return path;
    }
}
