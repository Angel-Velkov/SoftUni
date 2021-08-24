import java.util.*;

public class Tree23<K extends Comparable<K>> {

    private static class Node23<K> {
        private Node23<K> parent;
        private List<K> keys;
        private List<Node23<K>> children;

        private Node23(Node23<K> parent, K key) {
            this.parent = parent;
            this.keys = new ArrayList<>(List.of(key));
            this.children = new ArrayList<>();
        }

        private Node23(Node23<K> parent, K key, Node23<K> left, Node23<K> right) {
            this(parent, key);
            this.children = new ArrayList<>(Arrays.asList(left, right));
            left.parent = this;
            right.parent = this;
        }
    }

    private Node23<K> root;

    public void add(K key) {
        if (root == null) {
            root = new Node23<>(null, key);
        } else {
            add(root, key);
        }
    }

    private void add(Node23<K> node, K key) {
        Node23<K> insertionNode = node;
        int insertionIndex = getInsertionIndex(insertionNode, key);

        if (node.children.isEmpty()) {
            insertionNode.keys.add(insertionIndex, key);

            while (insertionNode.keys.size() > 2) {
                K midKey = insertionNode.keys.get(1);

                Node23<K> leftPart;
                Node23<K> rightPart;
                if (insertionNode.children.isEmpty()) {
                    leftPart = new Node23<>(insertionNode.parent, insertionNode.keys.get(0));
                    rightPart = new Node23<>(insertionNode.parent, insertionNode.keys.get(2));
                } else {
                    leftPart = new Node23<>(insertionNode.parent, insertionNode.keys.get(0),
                            insertionNode.children.get(0), insertionNode.children.get(1));
                    rightPart = new Node23<>(insertionNode.parent, insertionNode.keys.get(2),
                            insertionNode.children.get(0), insertionNode.children.get(1));
                }

                insertionNode = insertionNode.parent;
                if (insertionNode != null) {
                    int midKeyInsertionIndex = getInsertionIndex(insertionNode, midKey);
                    insertionNode.keys.add(midKeyInsertionIndex, midKey);
                    insertionNode.children.add(midKeyInsertionIndex, leftPart);
                    insertionNode.children.set(midKeyInsertionIndex + 1, rightPart);
                } else {
                    insertionNode = new Node23<>(null, midKey, leftPart, rightPart);
                    root = insertionNode;
                }
            }
        } else {
            add(node.children.get(insertionIndex), key);
        }
    }

    private int getInsertionIndex(Node23<K> node, K key) {
        for (int i = 0; i < node.keys.size(); i++) {
            if (isLessThan(key, node.keys.get(i))) {
                return i;
            }
        }

        return node.keys.size();
    }

    private boolean isLessThan(K a, K b) {
        return a.compareTo(b) < 0;
    }
}