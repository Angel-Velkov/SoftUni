import java.util.*;

public class StronglyConnectedComponents {
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static Deque<Integer> stack;
    private static List<Integer>[] reversedGraph;

    public static List<List<Integer>> findStronglyConnectedComponents(List<Integer>[] targetGraph) {
        graph = targetGraph;
        visited = new boolean[graph.length];
        stack = new ArrayDeque<>();

        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]){
                dfs(node);
            }
        }

        setReversedGraph();

        List<List<Integer>> stronglyConnectedComponents = new ArrayList<>();

        Arrays.fill(visited, false);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                List<Integer> connectedComponents = new ArrayList<>();
                reverseDfs(node, connectedComponents);
                stronglyConnectedComponents.add(connectedComponents);
            }
        }

        return stronglyConnectedComponents;
    }

    private static void dfs(int node){
        visited[node] = true;

        for (int child : graph[node]) {
            if (!visited[child]) {
                dfs(child);
            }
        }

        stack.push(node);
    }

    @SuppressWarnings("unchecked")
    private static void setReversedGraph() {
        reversedGraph = new ArrayList[graph.length];
        for (int node = 0; node < reversedGraph.length; node++) {
            reversedGraph[node] = new ArrayList<>();
        }

        for (int node = 0; node < graph.length; node++) {
            for (int child : graph[node]) {
                reversedGraph[child].add(node);
            }
        }
    }

    private static void reverseDfs(int node, List<Integer> connectedComponents) {
        visited[node] = true;

        for (int child : reversedGraph[node]) {
            if (!visited[child]) {
                reverseDfs(child, connectedComponents);
            }
        }

        connectedComponents.add(node);
    }
}
