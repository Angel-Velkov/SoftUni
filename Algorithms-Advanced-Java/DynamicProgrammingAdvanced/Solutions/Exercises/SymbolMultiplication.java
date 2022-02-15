import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolMultiplication {
    private static char[] alphabet;
    private static Map<Character, List<int[]>> multiplicationTable;
    private static String expression;

    private static Boolean[][][] memoization;
    private static int[][] split;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        alphabet = readAlphabet(reader.readLine());

        reader.readLine();
        multiplicationTable = readTable(alphabet, reader);

        expression = readExpression(reader.readLine());

        memoization = new Boolean[expression.length()][expression.length()][alphabet.length];

        split = new int[expression.length()][expression.length()];
        if (symbolMultiplication(0, expression.length() - 1, 0)) {
            StringBuilder result = new StringBuilder();
            putBrackets(0, expression.length() - 1, result);
            System.out.println(result);
        } else {
            System.out.println("No solution");
        }
    }

    private static boolean symbolMultiplication(int from, int to, int character) {
        if (memoization[from][to][character] != null) {
            return memoization[from][to][character];
        }

        if (from == to) {
            return expression.charAt(from) == character + 'a';
        }

        for (int[] multipliers : multiplicationTable.get(alphabet[character])) {
            for (int gap = from; gap < to; gap++) {
                if (symbolMultiplication(from, gap, multipliers[0])
                        && symbolMultiplication(gap + 1, to, multipliers[1])) {
                    memoization[from][to][character] = true;
                    split[from][to] = gap;
                    return true;
                }
            }
        }

        memoization[from][to][character] = false;
        return false;
    }

    private static void putBrackets(int a, int b, StringBuilder result) {
        if (a == b) {
            result.append(expression.charAt(a));
        } else {
            result.append("(");
            putBrackets(a, split[a][b], result);
            result.append("*");
            putBrackets(split[a][b] + 1, b, result);
            result.append(")");
        }
    }

    private static String readExpression(String line) {
        return line.trim().substring(line.lastIndexOf(" ") + 1);
    }

    private static char[] readAlphabet(String line) {
        return line
                .substring(line.indexOf("{") + 1, line.indexOf("}"))
                .replace(",", "")
                .toCharArray();
    }

    private static Map<Character, List<int[]>> readTable(char[] alphabet, BufferedReader reader) throws IOException {
        Map<Character, List<int[]>> table = new HashMap<>();

        for (int row = 0; row < alphabet.length; row++) {
            String line = reader.readLine().trim();
            for (int col = 0; col < alphabet.length; col++) {
                int[] multipliers = new int[]{row, col};

                table.computeIfAbsent(line.charAt(col), k -> new ArrayList<>())
                        .add(multipliers);
            }
        }

        return table;
    }
}
