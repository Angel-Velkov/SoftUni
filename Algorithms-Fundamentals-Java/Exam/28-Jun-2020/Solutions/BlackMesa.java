import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BlackMesa {

    private static final Map<Integer, Set<Integer>> graph = new HashMap<>();
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int versionsCount = Integer.parseInt(reader.readLine().trim());

        for (int i = 1; i <= versionsCount; i++) {
            graph.put(i, new TreeSet<>());
        }

        visited = new boolean[versionsCount + 1];

        int transitionsCount = Integer.parseInt(reader.readLine().trim());

        for (int i = 0; i < transitionsCount; i++) {
            int[] connection = Arrays.stream(reader.readLine().trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int prevVersion = connection[0];
            int nextVersion = connection[1];

            graph.get(prevVersion).add(nextVersion);
        }

        int start = Integer.parseInt(reader.readLine().trim());
        int target = Integer.parseInt(reader.readLine().trim());

        List<Integer> path = tourAllVersions(start, target);

        StringBuilder out = new StringBuilder();

        for (Integer node : path) {
            out.append(node).append(" ");
        }

        out.append(System.lineSeparator());

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                out.append(i).append(" ");
            }
        }

        System.out.println(out.toString().trim());
    }

    private static List<Integer> tourAllVersions(int from, int to) {
        ArrayList<Integer> toured = new ArrayList<>();

        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(from);

        boolean found = false;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = true;
            if (!found) {
                toured.add(node);
                found = (node == to);
            }

            for (Integer child : graph.get(node)) {
                if (!visited[child]) {
                    visited[child] = true;
                    queue.offer(child);
                }
            }
        }

        return toured;
    }
}
