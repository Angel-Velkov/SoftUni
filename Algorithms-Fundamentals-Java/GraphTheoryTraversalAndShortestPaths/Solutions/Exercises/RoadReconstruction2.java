import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RoadReconstruction2 {

    private static class Graph {
        int[][] matrix;

        Graph(int size) {
            this.matrix = new int[size][size];
        }

        void addEdge(int firstNode, int secondNode) {
            this.matrix[firstNode][secondNode] = 1;
            this.matrix[secondNode][firstNode] = 1;
        }

        void removeEdge(int first, int second) {
            this.matrix[first][second] = 0;
            this.matrix[second][first] = 0;
        }

        List<int[]> getAllEdges() {
            List<int[]> result = new ArrayList<>();

            for (int r = 0; r < this.matrix.length; r++) {
                for (int c = r + 1; c < this.matrix[r].length; c++) {
                    if (matrix[r][c] == 1) {
                        result.add(new int[]{r, c});
                    }
                }
            }

            return result;
        }

        List<Integer> getChildren(int node) {
            List<Integer> children = new ArrayList<>();

            for (int c = 0; c < this.matrix.length; c++) {
                if (this.matrix[node][c] == 1) {
                    children.add(c);
                }
            }
            return children;
        }

        int getSize() {
            return matrix.length;
        }
    }

    private static Graph graph;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int buildings = Integer.parseInt(reader.readLine());

        graph = new Graph(buildings);

        int streets = Integer.parseInt(reader.readLine());

        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < streets; i++) {
            int[] edge = Arrays.stream(reader.readLine().split("\\s*-\\s*"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            edges.add(edge);
            graph.addEdge(edge[0], edge[1]);
        }

        System.out.println("Important streets:");
        for (int[] edge : getImportantEdges(edges)) {
            System.out.println(edge[0] + " " + edge[1]);
        }
    }

    private static List<int[]> getImportantEdges(List<int[]> edges) {
        List<int[]> importantEdges = new ArrayList<>();

        for (int[] edge : edges) {
            graph.removeEdge(edge[0], edge[1]);

            if (!hasAPath(edge[0], edge[1])) {
                importantEdges.add(edge);
            } else {
                graph.addEdge(edge[0], edge[1]);
            }
        }

        return importantEdges;
    }

    private static boolean hasAPath(int source, int destination) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[graph.getSize()];

        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == destination) {
                return true;
            }

            for (int child : graph.getChildren(node)) {
                if (!visited[child]) {
                    queue.offer(child);
                    visited[child] = true;
                }
            }
        }
        return false;
    }
}
