import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AreasInMatrix {

    private static class Node {
        int[] value;
        List<Node> children;

        Node(int row, int col) {
            this.value = new int[]{row, col};
            this.children = new ArrayList<>();
        }

        void addChild(Node node) {
            this.children.add(node);
        }
    }

    private static char[][] matrix;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int rows = Integer.parseInt(reader.readLine());

        matrix = new char[rows][];
        visited = new boolean[rows][];
        for (int r = 0; r < matrix.length; r++) {
            String line = reader.readLine();
            matrix[r] = line.toCharArray();
            visited[r] = new boolean[line.length()];
        }

        Map<Character, List<Node>> graph = new TreeMap<>();
        findAllComponents(graph);

        System.out.println("Areas: " +
                graph
                        .values()
                        .stream()
                        .mapToInt(List::size)
                        .sum());

        graph.forEach(
                (letter, graphs) ->
                        System.out.printf("Letter '%s' -> %d%n",
                                letter, graphs.size())
        );
    }

    private static void findAllComponents(Map<Character, List<Node>> graph) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (!visited[r][c]) {
                    Node node = new Node(r, c);
                    dfs(node);
                    graph.computeIfAbsent(matrix[r][c], k -> new ArrayList<>()).add(node);
                }
            }
        }
    }

    private static void dfs(Node node) {
        int row = node.value[0];
        int col = node.value[1];

        char currentChar = AreasInMatrix.matrix[row][col];
        visited[row][col] = true;

        if (isInBounds(row, col + 1)
                && !visited[row][col + 1]
                && matrix[row][col + 1] == currentChar) {

            Node child = new Node(row, col + 1);
            node.addChild(child);
            dfs(child);
        }

        if (isInBounds(row, col - 1)
                && !visited[row][col - 1]
                && matrix[row][col - 1] == currentChar) {

            Node child = new Node(row, col - 1);
            node.addChild(child);
            dfs(child);
        }

        if (isInBounds(row + 1, col)
                && !visited[row + 1][col]
                && matrix[row + 1][col] == currentChar) {

            Node child = new Node(row + 1, col);
            node.addChild(child);
            dfs(child);
        }

        if (isInBounds(row - 1, col)
                && !visited[row - 1][col]
                && matrix[row - 1][col] == currentChar) {

            Node child = new Node(row - 1, col);
            node.addChild(child);
            dfs(child);
        }
    }

    private static boolean isInBounds(int row, int col) {
        return 0 <= row && row < matrix.length
                && 0 <= col && col < matrix[row].length;
    }
}
