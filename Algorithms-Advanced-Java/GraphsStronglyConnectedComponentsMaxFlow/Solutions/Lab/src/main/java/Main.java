import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer>[] graph = new ArrayList[6];

        graph[0] = new ArrayList<>(Arrays.asList(1, 3));
        graph[1] = new ArrayList<>(Arrays.asList(0, 2));
        graph[2] = new ArrayList<>(Arrays.asList(1, 3, 4, 5));
        graph[3] = new ArrayList<>(Arrays.asList(0, 2));
        graph[4] = new ArrayList<>(Arrays.asList(2, 5));
        graph[5] = new ArrayList<>(Arrays.asList(2, 4));

//        graph[0] = new ArrayList<>(Arrays.asList(1));
//        graph[1] = new ArrayList<>(Arrays.asList(2));
//        graph[2] = new ArrayList<>(Arrays.asList(3, 4));
//        graph[3] = new ArrayList<>(Arrays.asList(2));
//        graph[4] = new ArrayList<>(Arrays.asList(2));

        List<Integer> points = ArticulationPoints.findArticulationPoints(graph);

        System.out.println();
    }
}
