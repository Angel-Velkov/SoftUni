import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cinema {
    private static List<String> swappedSeats;
    private static String[] fixedSeats;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        swappedSeats = Arrays.stream(reader.readLine().split(",\\s*"))
                .collect(Collectors.toList());

        fixedSeats = new String[swappedSeats.size()];

        String seat;
        while (!"generate".equals(seat = reader.readLine())) {
            String[] data = seat.split("\\s*-\\s*");
            String name = data[0];
            int place = Integer.parseInt(data[1]) - 1;

            fixedSeats[place] = name;
            swappedSeats.remove(name);
        }

        permute(0);
    }

    private static void permute(int index) {
        if (index == swappedSeats.size()) {
            printSeats();
        } else {
            permute(index + 1);
            for (int i = index + 1; i < swappedSeats.size(); i++) {
                swap(index, i, swappedSeats);
                permute(index + 1);
                swap(index, i, swappedSeats);
            }
        }
    }

    private static void swap(int first, int second, List<String> list) {
        String temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }

    private static void printSeats() {
        String[] out = new String[fixedSeats.length];

        int index = 0;
        for (int i = 0; i < fixedSeats.length; i++) {
            out[i] = fixedSeats[i] == null
                    ? swappedSeats.get(index++)
                    : fixedSeats[i];
        }
        System.out.println(String.join(" ", out));
    }
}
