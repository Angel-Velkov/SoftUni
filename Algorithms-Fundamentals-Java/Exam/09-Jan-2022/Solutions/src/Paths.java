import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Paths {

    private static Map<Integer, List<Integer>> graph;
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine().trim());
        graph = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            graph.put(i, Arrays.stream(reader.readLine().split("\\s+"))
                    .filter(s -> !s.equals(""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));
        }

        for (int i = 0; i < n - 1; i++) {
            List<Integer> path = new LinkedList<>();
            dfs(i, n - 1, path);
        }

        System.out.println(result.toString().trim());
    }

    private static void dfs(int node, int destination, List<Integer> path) {
        path.add(node);

        if (node == destination) {
            addToTheResult(path);
        } else {
            for (int child : graph.get(node)) {
                dfs(child, destination, path);
            }
        }

        path.remove(path.size() - 1);
    }

    private static void addToTheResult(List<Integer> path) {
        result.append(
                        path.stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining(" "))
                )
                .append(System.lineSeparator());
    }
}
