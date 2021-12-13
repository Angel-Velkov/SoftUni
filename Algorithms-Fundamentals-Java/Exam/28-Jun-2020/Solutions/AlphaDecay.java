import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PrimitiveIterator;

public class AlphaDecay {

    private static int[] elements;
    private static boolean[] used;
    private static int[] slots;
    private static final StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        elements = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        used = new boolean[elements.length];

        int size = Integer.parseInt(reader.readLine());
        slots = new int[size];

        variations(0);

        System.out.println(result.toString().trim());
    }

    private static void variations(int index) {
        if (index >= slots.length) {
            saveTheVariation();
        } else {
            for (int i = 0; i < elements.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    slots[index] = elements[i];
                    variations(index + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void saveTheVariation() {
        PrimitiveIterator.OfInt iterator = Arrays.stream(slots).iterator();
        while (iterator.hasNext()) {
            result.append(iterator.next());
            if (iterator.hasNext()) {
                result.append(" ");
            }
        }
        result.append(System.lineSeparator());
    }
}
