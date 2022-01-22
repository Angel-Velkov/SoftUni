import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class MostReliablePath {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        double[][] graph = new double[nodesCount][nodesCount];

        String[] path = reader.readLine().split("\\s+");

        int edgesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        for (int i = 0; i < edgesCount; i++) {
            String[] tokens = reader.readLine().split("\\s+");

            int firstNode = Integer.parseInt(tokens[0]);
            int secondNode = Integer.parseInt(tokens[1]);
            double weight = Double.parseDouble(tokens[2]);

            graph[firstNode][secondNode] = weight;
            graph[secondNode][firstNode] = weight;
        }

        int source = Integer.parseInt(path[1]);
        int destination = Integer.parseInt(path[3]);

        double[] distance = new double[graph.length];
        distance[source] = 1.00;

        boolean[] visited = new boolean[graph.length];

        int[] previous = new int[graph.length];
        Arrays.fill(previous, -1);

        PriorityQueue<Integer> queue = new PriorityQueue<>((f, s) -> Double.compare(distance[s], distance[f]));
        queue.offer(source);
        queue.offer(destination);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = true;

            if (node == destination) {
                break;
            }

            for (int childNode = 0; childNode < graph[node].length; childNode++) {

                double edgeWeight = graph[node][childNode];
                if (edgeWeight != 0.00 && !visited[childNode]) {

                    double newDistance = distance[node] * (edgeWeight / 100.00);
                    if (newDistance > distance[childNode]) {
                        distance[childNode] = newDistance;
                        previous[childNode] = node;
                    }

                    queue.offer(childNode);
                }
            }
        }

        Deque<Integer> stack = new ArrayDeque<>();

        int node = destination;

        do {
            stack.push(node);
            node = previous[node];
        } while (node != -1);

        System.out.printf("Most reliable path reliability: %.2f%%%n", distance[destination] * 100);

        System.out.println(
                stack
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" -> "))
        );
    }
}
