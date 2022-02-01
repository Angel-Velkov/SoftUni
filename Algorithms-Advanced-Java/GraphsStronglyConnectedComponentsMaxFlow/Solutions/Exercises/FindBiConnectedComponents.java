import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FindBiConnectedComponents {

    private static class Graph {
        LinkedList<Integer>[] adjacency;

        public Graph(int vertices) {
            this.setAdjacencyList(vertices);
        }

        @SuppressWarnings("unchecked")
        private void setAdjacencyList(int vertices) {
            this.adjacency = new LinkedList[vertices];
            for (int i = 0; i < adjacency.length; i++) {
                adjacency[i] = new LinkedList<>();
            }
        }

        void addEdge(int v, int u) {
            this.adjacency[u].add(v);
            this.adjacency[v].add(u);
        }

        LinkedList<Integer> getChildren(int node) {
            return adjacency[node];
        }

        int size() {
            return adjacency.length;
        }
    }

    private static Graph graph;
    private static boolean[] visited;
    private static int[] parent;
    private static int[] depth;
    private static int[] lowpoint;
    private static Deque<Integer> stack;
    private static int componentsCount;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int edges = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        graph = new Graph(nodes);
        visited = new boolean[graph.size()];
        parent = new int[graph.size()];
        Arrays.fill(parent, -1);
        depth = new int[graph.size()];
        lowpoint = new int[graph.size()];
        stack = new ArrayDeque<>();

        for (int i = 0; i < edges; i++) {
            int[] edge = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph.addEdge(edge[0], edge[1]);
        }

        for (int node = 0; node < nodes; node++) {
            if (!visited[node]) {

                findArticulationPoints(node, 1);
                if (!stack.isEmpty()) {
                    stack.clear();
                    componentsCount++;
                }
            }
        }

        System.out.println("Number of bi-connected components: " + componentsCount);
    }

    private static void findArticulationPoints(int node, int level) {
        visited[node] = true;
        depth[node] = level;
        lowpoint[node] = level;

        int childCount = 0;

        for (int child : graph.getChildren(node)) {
            if (!visited[child]) {
                parent[child] = node;
                childCount++;

                stack.push(node);
                findArticulationPoints(child, level + 1);

                lowpoint[node] = Math.min(lowpoint[node], lowpoint[child]);

                if ((parent[node] != -1 && lowpoint[child] >= depth[node])
                        || (parent[node] == -1 && childCount > 1)) {

                    while (!stack.isEmpty() && stack.peek() != node) {
                        stack.pop();
                    }

                    componentsCount++;
                }

            } else if (parent[node] != child) {
                lowpoint[node] = Math.min(lowpoint[node], depth[child]);
                stack.push(node);
            }
        }
    }
}
