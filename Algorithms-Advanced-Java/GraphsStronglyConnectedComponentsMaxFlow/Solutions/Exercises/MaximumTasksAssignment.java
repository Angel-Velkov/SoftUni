import java.util.*;

public class MaximumTasksAssignment {

    private static int people;
    private static int tasks;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        people = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        tasks = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        boolean[][] btGraph = new boolean[tasks][people];

        for (int i = 0; i < tasks; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < people; j++) {
                btGraph[i][j] = (line.charAt(j) == 'Y');
            }
        }

        int[] match = maxBPM(btGraph);

        Map<Character, Integer> result = new TreeMap<>();
        for (int i = 0; i < match.length; i++) {
            if (match[i] != -1) {
                result.put((char) (match[i] + 'A'), i + 1);
            }
        }

        result.forEach((k, v) -> System.out.println(k + "-" + v));
    }

    private static int[] maxBPM(boolean[][] bpGraph) {
        int[] match = new int[tasks];
        Arrays.fill(match, -1);

        for (int person = 0; person < people; person++) {
            boolean[] visited = new boolean[tasks];
            bpm(bpGraph, person, visited, match);
        }

        return match;
    }

    private static boolean bpm(boolean[][] bpGraph, int person, boolean[] visited, int[] match) {
        for (int task = 0; task < tasks; task++) {
            if (bpGraph[task][person] && !visited[task]) {
                visited[task] = true;

                if (match[task] < 0 || bpm(bpGraph, match[task], visited, match)) {
                    match[task] = person;
                    return true;
                }
            }
        }

        return false;
    }
}
