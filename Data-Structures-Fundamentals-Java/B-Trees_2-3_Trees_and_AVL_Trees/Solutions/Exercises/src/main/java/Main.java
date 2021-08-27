import main.Hierarchy;

public class Main {
    public static void main(String[] args) {
        Hierarchy<Integer> hierarchy = new Hierarchy<>(4);
        hierarchy.add(4, 1);
        hierarchy.add(4, 2);
        hierarchy.add(4, 3);

        System.out.println(hierarchy.getCount());
    }
}