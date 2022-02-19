import java.util.*;
import java.util.stream.Collectors;

public class FoodProgramme {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        List<int[]>[] graph = new List[nodes];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        int edges = Integer.parseInt(scanner.nextLine());

        int source = scanner.nextInt();
        int destination = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            graph[from].add(new int[]{to, weight});
            graph[to].add(new int[]{from, weight});
        }

        findShortestPath(graph, source, destination);
    }

    private static void findShortestPath(List<int[]>[] graph, int source, int destination) {
        boolean[] visited = new boolean[graph.length];
        int[] distances = new int[graph.length];
        int[] previous = new int[graph.length];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        Arrays.fill(previous, -1);

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(node -> distances[node]));
        queue.offer(source);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = true;

            if (node == destination) {
                break;
            }

            for (int[] edge : graph[node]) {
                int children = edge[0];
                int weight = edge[1];
                if (!visited[children]) {
                    queue.offer(children);
                    int newDistance = distances[node] + weight;
                    if (newDistance < distances[children]) {
                        distances[children] = newDistance;
                        previous[children] = node;
                    }
                }
            }
        }

        System.out.println(
                getPath(previous, destination)
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );

        System.out.println(distances[destination]);
    }

    private static List<Integer> getPath(int[] previous, int destination) {
        if (previous[destination] == -1) {
            return Collections.emptyList();
        }

        List<Integer> path = new ArrayList<>();

        int node = destination;

        do {
            path.add(node);
            node = previous[node];
        } while (node != -1);

        Collections.reverse(path);

        return path;
    }
}
