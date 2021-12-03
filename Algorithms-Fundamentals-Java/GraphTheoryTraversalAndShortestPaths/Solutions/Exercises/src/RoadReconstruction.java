import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RoadReconstruction {

    // Unidirectional graph
    private static class Graph {
        int[][] matrix;

        Graph(int size) {
            matrix = new int[size][size];
        }

        void addEdge(int firstNode, int secondNode) {
            matrix[firstNode][secondNode] = 1;
            matrix[secondNode][firstNode] = 1;
        }

        List<Integer> getChildren(int node) {
            List<Integer> children = new ArrayList<>();

            for (int c = 0; c < matrix.length; c++) {
                if (matrix[node][c] == 1) {
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

        for (int i = 0; i < streets; i++) {
            int[] edge = Arrays.stream(reader.readLine().split("\\s+-\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph.addEdge(edge[0], edge[1]);
        }

        int[] color = new int[buildings];
        int[] par = new int[buildings];

        int[] mark = new int[buildings];

        dfsMarkCycles(0, -1, color, mark, par);

        System.out.println("Important streets:");
        printAcyclicEdges(mark);
    }

    private static void printAcyclicEdges(int[] mark) {
        for (int node = 0; node < graph.getSize(); node++) {
            if (mark[node] == 0) {
                for (Integer child : graph.getChildren(node)) {
                    if (child < node) {
                        System.out.println(child + " " + node);
                    } else {
                        System.out.println(node + " " + child);
                    }
                }
            }
        }
    }

    private static int cycleNumber = 0;

    static void dfsMarkCycles(int node, int parent, int[] color, int[] mark, int[] parents) {
        if (color[node] == 2) {
            return;
        }

        if (color[node] == 1) {
            cycleNumber++;
            int cur = parent;
            mark[cur] = cycleNumber;

            while (cur != node) {
                cur = parents[cur];
                mark[cur] = cycleNumber;
            }
            return;
        }
        parents[node] = parent;
        color[node] = 1;

        for (int child : graph.getChildren(node)) {
            if (child == parents[node]) {
                continue;
            }
            dfsMarkCycles(child, node, color, mark, parents);
        }

        color[node] = 2;
    }
}
