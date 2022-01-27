import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticulationPoints {

    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static int[] parent;
    private static int[] depth;
    private static int[] lowpoint;

    public static List<Integer> findArticulationPoints(List<Integer>[] targetGraph) {
        graph = targetGraph;
        visited = new boolean[graph.length];
        parent = new int[graph.length];
        Arrays.fill(parent, -1);
        depth = new int[graph.length];
        lowpoint = new int[graph.length];

        List<Integer> articulationPoints = new ArrayList<>();

        discoverArticulationPoint(0, 1, articulationPoints);

        return articulationPoints;
    }

    private static void discoverArticulationPoint(int node, int d, List<Integer> articulationPoints) {
        visited[node] = true;
        depth[node] = d;
        lowpoint[node] = d;

        int childCount = 0;

        boolean isArticulation = false;

        for (int child : graph[node]) {
            if (!visited[child]) {
                parent[child] = node;
                childCount++;

                discoverArticulationPoint(child, d + 1, articulationPoints);

                if (lowpoint[child] >= depth[node]) {
                    isArticulation = true;
                }

                lowpoint[node] = Math.min(lowpoint[node], lowpoint[child]);

            } else if (parent[node] != child) {
                lowpoint[node] = Math.min(lowpoint[node], depth[child]);
            }
        }

        if ((parent[node] != -1 && isArticulation)
                || (parent[node] == -1 && childCount > 1)) {
            articulationPoints.add(node);
        }
    }
}
