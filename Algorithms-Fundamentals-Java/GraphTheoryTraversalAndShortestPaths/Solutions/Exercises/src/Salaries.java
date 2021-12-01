import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Salaries {
    private static int[][] graph;
    private static int[] salaries;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());


        graph = new int[n][n];

        for (int r = 0; r < n; r++) {
            String line = reader.readLine();
            for (int c = 0; c < n; c++) {
                graph[r][c] = line.charAt(c) == 'Y' ? 1 : 0;
            }
        }

        salaries = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i == graph[j][i]) {
                    System.out.println(i);
                }
            }
        }
    }
}
