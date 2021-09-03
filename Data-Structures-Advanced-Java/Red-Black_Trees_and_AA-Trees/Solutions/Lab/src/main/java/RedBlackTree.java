import java.util.function.Consumer;

public class RedBlackTree<T extends Comparable<T>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public static class Node<T extends Comparable<T>> {
        private T value;
        private Node<T> left;
        private Node<T> right;
        private boolean color;
        private int count;

        public Node() {
        }

        private Node(T value, boolean color) {
            this.value = value;
            this.color = color;
        }
    }

    private Node<T> root;

    public RedBlackTree() {
    }

    private RedBlackTree(Node<T> node) {
        this.preOrderCopy(node);
    }

    private boolean isRed(Node<T> node) {
        return node != null && node.color != BLACK;
    }

    private Node<T> leftRotate(Node<T> node) {
        Node<T> right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = RED;
        node.count = 1 + count(node.left) + count(node.right);

        return right;
    }

    private Node<T> rightRotate(Node<T> node) {
        Node<T> left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = RED;
        node.count = 1 + count(node.left) + count(node.right);

        return left;
    }

    private void flipColors(Node<T> node) {
        if (node == null) {
            return;
        }

        if (node.color == RED) {
            node.color = BLACK;
        } else {
            node.color = RED;
        }
    }

    private int count(Node<T> node) {
        if (node == null) {
            return 0;
        }

        return node.count;
    }

    private void preOrderCopy(Node<T> node) {
        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.preOrderCopy(node.left);
        this.preOrderCopy(node.right);
    }

    public int getNodesCount() {
        return this.getNodesCount(this.root);
    }

    private int getNodesCount(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return node.count;
    }

    // TODO:
    //  Insert using iteration over the nodes
    //  You can make a recursive one it is up to you
    //  The difference is that the recursive call should
    //  return Node
    public void insert(T value) {
        this.root = this.insert(value, this.root);
        this.root.color = BLACK;
    }

    private Node<T> insert(T value, Node<T> node) {
        if (node == null) {
            node= new Node<>(value, RED);
        }

        return node;
    }

    public boolean contains(T value) {
        return this.findElement(value) != null;
    }

    public RedBlackTree<T> search(T item) {
        return new RedBlackTree<>(this.findElement(item));
    }

    private Node<T> findElement(T item) {
        Node<T> current = this.root;
        while (current != null) {
            if (item.compareTo(current.value) < 0) {
                current = current.left;
            } else if (item.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return current;
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.right, consumer);
    }
}

