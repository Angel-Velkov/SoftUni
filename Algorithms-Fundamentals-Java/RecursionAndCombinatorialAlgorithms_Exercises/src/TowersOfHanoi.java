import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;

public class TowersOfHanoi {
    private static int step = 0;

    private static final ArrayDeque<Integer> source = new ArrayDeque<>();
    private static final ArrayDeque<Integer> destination = new ArrayDeque<>();
    private static final ArrayDeque<Integer> spare = new ArrayDeque<>();

    private static final StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int disk = Integer.parseInt(reader.readLine());

        for (int i = disk; i >= 1; i--) {
            source.push(i);
        }
        output.append(rodsInfo())
                .append(System.lineSeparator());
        solve(disk, source, destination, spare);

        System.out.println(output);
    }

    private static void solve(int disk, ArrayDeque<Integer> source, ArrayDeque<Integer> destination, ArrayDeque<Integer> spare) {
        if (disk == 1) {
            destination.push(source.pop());
            output.append("Step #").append(++step).append(": Moved disk")
                    .append(System.lineSeparator());
            output.append(rodsInfo())
                    .append(System.lineSeparator());
        } else {
            solve(disk - 1, source, spare, destination);
            solve(1, source, destination, spare);
            solve(disk - 1, spare, destination, source);
        }
    }

    private static String rodsInfo() {
        return String.format("Source: %s%n" +
                        "Destination: %s%n" +
                        "Spare: %s%n"
                , stackFormat(source, ", ")
                , stackFormat(destination, ", ")
                , stackFormat(spare, ", "));
    }

    private static String stackFormat(ArrayDeque<Integer> stack, String delimiter) {
        StringBuilder out = new StringBuilder();

        Iterator<Integer> iterator = stack.descendingIterator();
        while (iterator.hasNext()) {
            out.append(iterator.next());
            if (iterator.hasNext()) {
                out.append(delimiter);
            }
        }
        return out.toString();
    }
}
