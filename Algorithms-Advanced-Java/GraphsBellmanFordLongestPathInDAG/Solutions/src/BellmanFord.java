import java.util.*;
import java.util.stream.Collectors;

public class BellmanFord {
    public static List<Integer> findShortestPath(Integer[][] graph, int source, int destination) {
        int[] distance = new int[graph.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        int[] previous = new int[graph.length];
        Arrays.fill(previous, -1);

        for (int i = 1; i < graph.length; i++) {
            boolean hasChanged = false;

            for (int r = 1; r < graph.length; r++) {
                for (int c = 1; c < graph[r].length; c++) {

                    Integer weight = graph[r][c];
                    if (weight != null && distance[r] != Integer.MAX_VALUE) {

                        int newWeight = distance[r] + weight;
                        if (newWeight < distance[c]) {
                            distance[c] = newWeight;
                            previous[c] = r;
                            hasChanged = true;

                            if (i == graph.length - 1) {
                                throw new IllegalStateException(
                                        "Negative Cycle Detected"
                                );
                            }
                        }
                    }
                }
            }

            if (!hasChanged) {
                break;
            }
        }

        return convertIntoAPath(previous, destination);
    }

    private static List<Integer> convertIntoAPath(int[] previous, int from) {
        List<Integer> path = new ArrayList<>();

        int step = from;

        do {
            path.add(step);
            step = previous[step];
        } while (step != -1);

        Collections.reverse(path);

        return path;
    }

    public static void printPathInfo(Integer[][] graph, List<Integer> shortestPath) {
        System.out.println(
                shortestPath
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );

        System.out.println(getPathWeight(graph, shortestPath));
    }

    private static int getPathWeight(Integer[][] graph, List<Integer> shortestPath) {
        int sum = 0;

        int prevStep = -1;
        for (Integer step : shortestPath) {
            if (prevStep != -1) {
                sum += graph[prevStep][step];
            }
            prevStep = step;
        }

        return sum;
    }
}
