import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Pipelines {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int agentsCount = Integer.parseInt(reader.readLine());
        int pipelinesCount = Integer.parseInt(reader.readLine());

        List<String> agents = new ArrayList<>();
        for (int i = 0; i < agentsCount; i++) {
            agents.add(reader.readLine());
        }

        Map<String, Integer> pipelinesIndexes = new HashMap<>();
        List<String> pipelines = new ArrayList<>();
        for (int i = 0; i < pipelinesCount; i++) {
            String line = reader.readLine();
            pipelines.add(line);
            pipelinesIndexes.put(line, i);
        }

        boolean[][] btGraph = new boolean[pipelinesCount][agentsCount];

        for (int i = 0; i < agentsCount; i++) {
            String[] tokens = reader.readLine().split(",\\s+");
            int agent = agents.indexOf(tokens[0]);

            for (int j = 1; j < tokens.length; j++) {
                btGraph[pipelinesIndexes.get(tokens[j])][agent] = true;
            }
        }

        int[] match = maxBPM(btGraph, pipelinesCount, agentsCount);

        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < match.length; i++) {
            if (match[i] != -1) {
                result.put(agents.get(match[i]), pipelines.get(i));
            }
        }

        result.forEach((k, v) -> System.out.println(k + " - " + v));
    }

    private static int[] maxBPM(boolean[][] bpGraph, int tasks, int people) {
        int[] match = new int[tasks];
        Arrays.fill(match, -1);

        for (int person = 0; person < people; person++) {
            boolean[] visited = new boolean[tasks];
            bpm(bpGraph, person, visited, match, tasks);
        }

        return match;
    }

    private static boolean bpm(boolean[][] bpGraph, int person, boolean[] visited, int[] match, int tasks) {
        for (int task = 0; task < tasks; task++) {
            if (bpGraph[task][person] && !visited[task]) {
                visited[task] = true;

                if (match[task] < 0 || bpm(bpGraph, match[task], visited, match, tasks)) {
                    match[task] = person;
                    return true;
                }
            }
        }

        return false;
    }
}
