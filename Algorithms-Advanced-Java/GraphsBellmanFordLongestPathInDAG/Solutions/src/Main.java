import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        int edges = Integer.parseInt(scanner.nextLine());

        Integer[][] graph = new Integer[nodes + 1][nodes + 1];

        for (int i = 0; i < edges; i++) {
            int[] edgeInfo = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int source = edgeInfo[0];
            int destination = edgeInfo[1];
            int weight = edgeInfo[2];

            graph[source][destination] = weight;
        }

        int source = Integer.parseInt(scanner.nextLine());
        int destination = Integer.parseInt(scanner.nextLine());

        try {
            // 1. Bellman-Ford
//            List<Integer> shortestPath = BellmanFord.findShortestPath(graph, source, destination);
//            BellmanFord.printPathInfo(graph, shortestPath);

            // 2. Longest Path
//            System.out.println(LongestPath.getWeightOfLongestPath(graph, source, destination));
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
