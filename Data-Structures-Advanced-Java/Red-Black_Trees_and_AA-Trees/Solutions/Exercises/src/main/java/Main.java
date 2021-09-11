public class Main {
    public static void main(String[] args) {
        String[] input = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};

        RedBlackTree<String, Integer> redBlackTree = new RedBlackTree<>();

        for (int i = 0; i < input.length; i++) {
            redBlackTree.put(input[i], i);
            redBlackTree.printPreOrder();
            System.out.println("<=================================>");
        }
    }
}
