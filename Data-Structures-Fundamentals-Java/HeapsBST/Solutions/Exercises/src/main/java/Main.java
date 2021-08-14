import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//        bst.insert(7);
//        bst.insert(5);
//        bst.insert(3);
//        bst.insert(2);
//        bst.insert(1);
//        bst.insert(4);
//        bst.insert(6);
//        bst.insert(8);
//        bst.insert(10);
//        bst.insert(11);

//        bst.insert(5);
//        bst.insert(4);
//        bst.insert(2);
//        bst.insert(3);

//        bst.insert(10);
//        bst.insert(7);
//        bst.insert(2);
//        bst.insert(1);
//        bst.insert(4);
//        bst.insert(3);
//        bst.insert(6);
//        bst.insert(9);
//        bst.insert(8);

        bst.insert(7);
        bst.insert(5);
        bst.insert(3);
        bst.insert(2);
        bst.insert(1);
        bst.insert(4);
        bst.insert(6);
        bst.insert(8);
        bst.insert(10);
        bst.insert(11);

        int bound = new Scanner(System.in).nextInt();

        System.out.println(bst.ceil(bound));
    }
}