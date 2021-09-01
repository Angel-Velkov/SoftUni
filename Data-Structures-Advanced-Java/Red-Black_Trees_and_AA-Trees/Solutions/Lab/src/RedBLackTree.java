public class RedBLackTree<K extends Comparable<K>> {
    private enum Color {
        RED,
        BLACK
    }

    private static class Node<K> {
        K key;
        Node<K> parent;
        Node<K> left;
        Node<K> right;
        Color color;

        Node(K key, Node<K> parent) {
            this.key = key;
            this.parent = parent;
            this.color = parent == null
                    ? Color.BLACK
                    : Color.RED;
        }

        Node(K key) {
            this(key, null);
        }

        void setLeft(Node<K> node) {
            this.left = node;

            if (node != null) {
                node.parent = this;
            }
        }

        void setRight(Node<K> node) {
            this.right = node;

            if (node != null) {
                node.parent = this;
            }
        }

        Color getUncleColor() {
            Node<K> uncle = getUncle();

            if (uncle != null) {
                return uncle.color;
            }

            return Color.BLACK;
        }

        Node<K> getUncle() {
            Node<K> grandparent = getGrandParent();

            if (grandparent == null) {
                return null;
            }

            if (this.parent == grandparent.left) {
                return grandparent.right;
            } else {
                return grandparent.left;
            }

        }

        Node<K> getGrandParent() {
            if (this.parent == null) {
                return null;
            }

            return this.parent.parent;
        }

        @Override
        public String toString() {
            return color + "(" + key + ")";
        }
    }

    private Node<K> root;

    public void add(K key) {
        if (this.root == null) {
            this.root = new Node<>(key);
        } else {
            Node<K> parent = this.root;

            Node<K> redNode = null;
            while (redNode == null) {
                if (isLessThan(key, parent.key)) {
                    if (parent.left == null) {
                        redNode = new Node<>(key, parent);
                        parent.left = redNode;
                    } else {
                        parent = parent.left;
                    }
                } else {
                    if (parent.right == null) {
                        redNode = new Node<>(key, parent);
                        parent.right = redNode;
                    } else {
                        parent = parent.right;
                    }
                }
            }
            ensureBalance(redNode);
        }
    }

    private void ensureBalance(Node<K> redNode) {
    }

    private boolean isLessThan(K first, K second) {
        return first.compareTo(second) < 0;
    }
}