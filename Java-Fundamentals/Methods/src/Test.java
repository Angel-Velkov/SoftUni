import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> line = new ArrayList<>(Arrays.asList("goal", "goal", "hate", "goal", "hate", "goal"));

        while (!line.remove("goal")){
        }
        for (String s : line) {
            System.out.println(s);
        }
    }
}
