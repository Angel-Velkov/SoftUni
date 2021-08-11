package Lab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchTree<K extends Comparable<K>> {
    private static class Node<K> {
        private K key;
        private Node<K> left;
        private Node<K> right;

        private Node(K key, Node<K> left, Node<K> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public Node<K> getLeft() {
            return left;
        }

        public void setLeft(Node<K> left) {
            this.left = left;
        }

        public Node<K> getRight() {
            return right;
        }

        public void setRight(Node<K> right) {
            this.right = right;
        }
    }

    private Node<K> root;

    public BinarySearchTree() {
    }

    private BinarySearchTree(Node<K> node) {
        this.root = node;
    }

    public Node<K> getRoot() {
        return this.root;
    }

    public void insert(K key) {
        Node<K> newNode = new Node<>(key, null, null);

        if (this.root == null) {
            this.root = newNode;
        }

        Node<K> currentNode = this.root;
        while (true) {
            int compare = key.compareTo(currentNode.getKey());
            if (compare < 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(newNode);
                    return;
                } else {
                    currentNode = currentNode.getLeft();
                }
            } else if (compare > 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(newNode);
                    return;
                } else {
                    currentNode = currentNode.getRight();
                }
            } else {
                return;
            }
        }
    }

    public BinarySearchTree<K> copy() {
        return new BinarySearchTree<>(copy(this.root));
    }

    public Node<K> copy(Node<K> node) {
        if (node == null) {
            return null;
        }

        return new Node<>(
                node.getKey(),
                copy(node.getLeft()),
                copy(node.getRight())
        );
    }

    public void print() {
        print(this.root);
    }

    public void print(Node<K> node) {
        if (node == null) {
            return;
        }
        print(node.getLeft());
        System.out.println(node.getKey());
        print(node.getRight());
    }

    static class BTreePrinter {

        public static <K extends Comparable<K>> void printNode(Node<K> root) {
            int maxLevel = BTreePrinter.maxLevel(root);
            StringBuilder buffer = new StringBuilder();

            getNodeInternal(Collections.singletonList(root), 1, maxLevel, buffer);
            System.out.println(buffer);
        }

        private static <K extends Comparable<K>> void getNodeInternal(List<Node<K>> nodes, int level, int maxLevel, StringBuilder buffer) {
            if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes)) {
                return;
            }

            int floor = maxLevel - level;
            int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            BTreePrinter.printWhitespaces(firstSpaces, buffer);

            List<Node<K>> newNodes = new ArrayList<>();
            for (Node<K> node : nodes) {
                if (node != null) {
                    buffer.append(node.getKey());
                    newNodes.add(node.getLeft());
                    newNodes.add(node.getRight());
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    buffer.append(" ");
                }

                BTreePrinter.printWhitespaces(betweenSpaces, buffer);
            }
            buffer.append(System.lineSeparator());

            for (int i = 1; i <= edgeLines; i++) {
                for (Node<K> node : nodes) {
                    BTreePrinter.printWhitespaces(firstSpaces - i, buffer);
                    if (node == null) {
                        BTreePrinter.printWhitespaces(edgeLines + edgeLines + i + 1, buffer);
                        continue;
                    }

                    if (node.getLeft() != null) {
                        buffer.append("/");
                    } else {
                        BTreePrinter.printWhitespaces(1, buffer);
                    }
                    BTreePrinter.printWhitespaces(i + i - 1, buffer);

                    if (node.getRight() != null) {
                        buffer.append("\\");
                    } else {
                        BTreePrinter.printWhitespaces(1, buffer);
                    }
                    BTreePrinter.printWhitespaces(edgeLines + edgeLines - i, buffer);
                }
                buffer.append(System.lineSeparator());
            }
            getNodeInternal(newNodes, level + 1, maxLevel, buffer);
        }

        private static void printWhitespaces(int count, StringBuilder buffer) {
            buffer.append(" ".repeat(Math.max(0, count)));
        }

        private static <K extends Comparable<K>> int maxLevel(Node<K> node) {
            if (node == null) {
                return 0;
            }
            return Math.max(BTreePrinter.maxLevel(node.getLeft()), BTreePrinter.maxLevel(node.getRight())) + 1;
        }

        private static <K> boolean isAllElementsNull(List<K> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }

            return true;
        }
    }
}