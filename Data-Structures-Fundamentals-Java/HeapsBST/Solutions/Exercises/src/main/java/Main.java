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

//        bst.insert(9);
//        bst.insert(6);
//        bst.insert(8);
//        bst.insert(7);
//        bst.insert(2);
//        bst.insert(1);
//        bst.insert(3);
//        bst.insert(4);
//        bst.insert(5);

//        bst.insert(10);
//        bst.insert(11);
//        bst.insert(12);
//        bst.insert(13);
//        bst.insert(20);
//        bst.insert(14);
//        bst.insert(15);
//        bst.insert(18);
//        bst.insert(16);

//        bst.insert(50);
//        bst.insert(48);
//        bst.insert(43);
//        bst.insert(19);
//        bst.insert(8);
//        bst.insert(10);
//        bst.insert(15);
//        bst.insert(11);
//        bst.insert(13);

        bst.insert(10);
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(16);
        bst.insert(12);
        bst.insert(13);

        System.out.println(bst.rank(15));
    }
}