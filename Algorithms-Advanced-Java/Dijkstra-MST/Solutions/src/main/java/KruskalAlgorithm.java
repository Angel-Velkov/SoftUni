import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class KruskalAlgorithm {

    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {
        List<Edge> forest = new ArrayList<>();

        LinkedList<Edge> sortedEdges = new LinkedList<>(edges);
        Collections.sort(sortedEdges);

        int[] parents = new int[numberOfVertices];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        while (!sortedEdges.isEmpty()) {
            Edge edge = sortedEdges.pollFirst();

            int firstRoot = findRoot(edge.getStartNode(), parents);
            int secondRoot = findRoot(edge.getEndNode(), parents);

            if (firstRoot != secondRoot) {
                forest.add(edge);
                parents[secondRoot] = firstRoot;
            }
        }

        return forest;
    }

    public static int findRoot(int node, int[] parents) {
        while (node != parents[node]) {
            node = parents[node];
        }

        return node;
    }
}
