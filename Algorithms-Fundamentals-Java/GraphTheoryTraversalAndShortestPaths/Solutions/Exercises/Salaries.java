import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Salaries {
    private static char[][] graph;
    private static long[] salaries;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        graph = new char[n][n];

        for (int r = 0; r < n; r++) {
            
            String line = reader.readLine();
            for (int c = 0; c < n; c++) {
                graph[r][c] = line.charAt(c);
            }
        }

        salaries = new long[n];

        for (int i = 0; i < n; i++) {
            boolean hasAManager = false;

            for (int j = 0; j < n; j++) {
                if (i != j && graph[j][i] == 'Y') {
                    hasAManager = true;
                    break;
                }
            }

            if (!hasAManager) {
                calculateSalary(i);
            }
        }

        /*
        for (int i = 0; i < salaries.length; i++) {
            System.out.println(i + " -> " + salaries[i]);
        }
        */

        System.out.println(Arrays.stream(salaries).sum());
    }

    private static long calculateSalary(int employee) {
        if (salaries[employee] != 0) {
            return salaries[employee];
        }

        for (int i = 0; i < graph[employee].length; i++) {
            if (employee != i && graph[employee][i] == 'Y') {
                salaries[employee] += calculateSalary(i);
            }
        }

        if (salaries[employee] == 0) {
            salaries[employee] = 1;
        }

        return salaries[employee];
    }
}