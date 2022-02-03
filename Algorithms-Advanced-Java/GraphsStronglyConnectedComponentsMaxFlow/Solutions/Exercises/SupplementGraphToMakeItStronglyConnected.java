import java.util.*;

public class SupplementGraphToMakeItStronglyConnected {

    private static class Graph {
        Set<Integer>[] adjacency;

        public Graph(int nodes) {
            this.setAdjacency(nodes);
        }

        @SuppressWarnings("unchecked")
        void setAdjacency(int nodes) {
            this.adjacency = new Set[nodes];
            for (int node = 0; node < nodes; node++) {
                adjacency[node] = new HashSet<>();
            }
        }

        void addEdge(int from, int to) {
            adjacency[from].add(to);
        }

        Set<Integer> getChildren(int node) {
            return adjacency[node];
        }

        int size() {
            return adjacency.length;
        }
    }

    private static Graph graph;
    private static Graph reversedGraph;
    private static boolean[] visited;
    private static Deque<Integer> stack;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        graph = new Graph(nodes);
        reversedGraph = new Graph(nodes);

        int edges = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        for (int i = 0; i < edges; i++) {
            int[] edge = Arrays.stream(scanner.nextLine().split("\\s*->\\s*"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int source = edge[0];
            int destination = edge[1];

            graph.addEdge(source, destination);
            reversedGraph.addEdge(destination, source);
        }

        List<Set<Integer>> scc = findStronglyConnectedComponents();

        Set<Integer>[] inDegrees = new Set[scc.size()];
        Set<Integer>[] outDegrees = new Set[scc.size()];
        for (int component = 0; component < scc.size(); component++) {
            inDegrees[component] = new HashSet<>();
            outDegrees[component] = new HashSet<>();
        }

        findAllDegrees(scc, inDegrees, outDegrees);

        System.out.println(
                "New edges needed: " +
                        Math.max(
                                Arrays.stream(inDegrees).filter(Set::isEmpty).count(),
                                Arrays.stream(outDegrees).filter(Set::isEmpty).count()
                        )
        );
    }

    private static void findAllDegrees(List<Set<Integer>> scc, Set<Integer>[] inDegrees, Set<Integer>[] outDegrees) {
        for (int component = 0; component < scc.size(); component++) {
            Set<Integer> componentNodes = scc.get(component);

            int start = componentNodes
                    .stream()
                    .findFirst()
                    .orElseThrow(
                            () -> new IllegalStateException("Empty component")
                    );

            List<Integer> outgoingNodes = bfs(start, componentNodes);
            for (int node : outgoingNodes) {
                for (int innerComponent = 0; innerComponent < scc.size(); innerComponent++) {
                    if (innerComponent != component && scc.get(innerComponent).contains(node)) {
                        inDegrees[innerComponent].add(component);
                        outDegrees[component].add(innerComponent);
                    }
                }
            }
        }
    }

    private static List<Integer> bfs(int start, Set<Integer> componentNodes) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> outgoingNodes = new ArrayList<>();

        visited.add(start);
        queue.offer(start);

        int node;
        while (!queue.isEmpty()) {
            node = queue.poll();

            if (componentNodes.contains(node)) {
                for (int child : graph.getChildren(node)) {
                    if (!visited.contains(child)) {
                        visited.add(child);
                        queue.offer(child);
                    }
                }
            } else {
                outgoingNodes.add(node);
            }
        }

        return outgoingNodes;
    }

    private static List<Set<Integer>> findStronglyConnectedComponents() {
        visited = new boolean[graph.size()];
        stack = new ArrayDeque<>();
        for (int node = 0; node < graph.size(); node++) {
            if (!visited[node]) {
                dfs(node);
            }
        }

        List<Set<Integer>> stronglyConnectedComponents = new ArrayList<>();

        Arrays.fill(visited, false);
        while (!stack.isEmpty()) {
            Set<Integer> component = new HashSet<>();
            int node = stack.pop();

            if (!visited[node]) {
                reverseDfs(node, component);
                stronglyConnectedComponents.add(component);
            }
        }

        return stronglyConnectedComponents;
    }

    private static void dfs(int node) {
        visited[node] = true;

        for (int child : graph.getChildren(node)) {
            if (!visited[child]) {
                dfs(child);
            }
        }

        stack.push(node);
    }

    private static void reverseDfs(int node, Set<Integer> component) {
        visited[node] = true;

        for (int child : reversedGraph.getChildren(node)) {
            if (!visited[child]) {
                reverseDfs(child, component);
            }
        }

        component.add(node);
    }
}
