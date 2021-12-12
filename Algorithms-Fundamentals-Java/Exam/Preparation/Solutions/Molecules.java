import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Molecules {

    private static Map<Integer, List<Integer>> graph;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int molecule = Integer.parseInt(reader.readLine());
        int connections = Integer.parseInt(reader.readLine());

        graph = new HashMap<>();

        for (int i = 1; i <= molecule; i++) {
            graph.put(i, new ArrayList<>());
        }

        visited = new boolean[molecule];

        for (int i = 0; i < connections; i++) {
            int[] edge = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int source = edge[0];
            int destination = edge[1];

            graph.get(source).add(destination);
        }

        int startMoleculeNumber = Integer.parseInt(reader.readLine());

        markAllReachablePaths(startMoleculeNumber);

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                System.out.print((i + 1) + " ");
            }
        }
    }

    private static void markAllReachablePaths(int from) {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(from);
        visited[from - 1] = true;

        while (!queue.isEmpty()) {
            int molecule = queue.poll();

            for (Integer child : graph.get(molecule)) {
                if (!visited[child - 1]) {
                    visited[child - 1] = true;
                    queue.offer(child);
                }
            }
        }
    }
}
