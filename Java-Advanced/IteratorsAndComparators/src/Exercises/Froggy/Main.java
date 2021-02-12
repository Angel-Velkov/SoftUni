package Exercises.Froggy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> stones = new ArrayList<>();

        String input;
        while (!"END".equals(input = scanner.next())){
            int num = Integer.parseInt(input.split(",*")[0]);
            stones.add(num);
        }

        Lake lake = new Lake(stones.stream().mapToInt(i -> i).toArray());
        Iterator<Integer> frog = lake.iterator();

        StringBuilder output = new StringBuilder();
        while (frog.hasNext()){
            output.append(frog.next());
            if (frog.hasNext()){
                output.append(", ");
            }
        }
        System.out.println(output);
    }
}
