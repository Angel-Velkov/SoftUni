import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;
    private int size;

    public static class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> leftChild, Node<E> rightChild) {
            this(value);
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public Node<E> getLeft() {
            return this.leftChild;
        }

        public Node<E> getRight() {
            return this.rightChild;
        }

        public E getValue() {
            return this.value;
        }

        public void setLeftChild(Node<E> leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(Node<E> rightChild) {
            this.rightChild = rightChild;
        }
    }

    public BinarySearchTree() {
    }

    private BinarySearchTree(Node<E> node) {
        this.root = node;
    }

    public void eachInOrder(Consumer<E> consumer) {
        eachInOrder(consumer, this.root);
    }

    private void eachInOrder(Consumer<E> consumer, Node<E> node) {
        if (node == null) {
            return;
        }

        if (node.getLeft() != null) {
            eachInOrder(consumer, node.leftChild);
        }

        consumer.accept(node.getValue());

        if (node.getRight() != null) {
            eachInOrder(consumer, node.rightChild);
        }
    }

    public Node<E> getRoot() {
        return this.root;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void insert(E element) {
        if (this.root == null) {
            this.root = new Node<>(element);
        } else {
            insert(element, this.root);
        }

        this.size++;
    }

    private void insert(E element, Node<E> node) {
        int compare = element.compareTo(node.value);

        if (compare < 0) {
            if (node.getLeft() == null) {
                node.leftChild = new Node<>(element);
            } else {
                insert(element, node.getLeft());
            }
        } else if (compare > 0) {
            if (node.getRight() == null) {
                node.rightChild = new Node<>(element);
            } else {
                insert(element, node.getRight());
            }
        }
    }

    public boolean contains(E element) {
        Node<E> node = root;

        while (node != null) {
            int compare = element.compareTo(node.getValue());

            if (compare == 0) {
                return true;
            }

            if (compare > 0) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
        }

        return false;
    }

    public BinarySearchTree<E> search(E element) {
        Node<E> node = this.root;

        while (node != null) {
            int compare = element.compareTo(node.getValue());

            if (compare == 0) {
                break;
            }

            if (compare > 0) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
        }

        BinarySearchTree<E> bst = new BinarySearchTree<>(copy(node));
        bst.setSize(count(bst.root));

        return bst;
    }

    private Node<E> copy(Node<E> node) {
        if (node == null) {
            return null;
        }

        return new Node<>(
                node.getValue(),
                copy(node.getLeft()),
                copy(node.getRight())
        );
    }

    public List<E> range(E first, E second) {
        List<E> elements = new ArrayList<>();
        fillInOrder(elements, this.root, first, second);

        /*
        this.eachInOrder(e -> {
            if (e.compareTo(first) >= 0 && e.compareTo(second) <= 0) {
                elements.add(e);
            }
        });
        */

        return elements;
    }

    private void fillInOrder(List<E> list, Node<E> parent, E from, E to) {
        if (parent == null || from.compareTo(to) > 0) {
            return;
        }

        Node<E> leftChild = parent.getLeft();
        if (leftChild != null) {
            fillInOrder(list, leftChild, from, to);
        }

        if (isBetweenInclusive(parent, from, to)) {
            list.add(parent.getValue());
        }

        Node<E> rightChild = parent.getRight();
        if (rightChild != null) {
            fillInOrder(list, rightChild, from, to);
        }
    }

    private boolean isBetweenInclusive(Node<E> node, E from, E to) {
        int lowerBound = from.compareTo(node.getValue());
        int upperBound = to.compareTo(node.getValue());

        return lowerBound <= 0 && upperBound >= 0;
    }

    public void deleteMin() {
        ensureNonEmpty();

        if (this.root.getLeft() == null) {
            this.root = this.root.getRight();
        } else {
            Node<E> currentNode = this.root;

            while (currentNode.getLeft().getLeft() != null) {
                currentNode = currentNode.getLeft();
            }

            currentNode.leftChild = currentNode.getLeft().getRight();
        }

        this.size--;
    }

    public void deleteMax() {
        ensureNonEmpty();

        if (this.root.getRight() == null) {
            this.root = this.root.getLeft();
        } else {
            Node<E> currentNode = this.root;

            while (currentNode.getRight().getRight() != null) {
                currentNode = currentNode.getRight();
            }

            currentNode.rightChild = currentNode.getRight().getLeft();
        }

        this.size--;
    }

    private void ensureNonEmpty() {
        if (this.root == null) {
            throw new IllegalArgumentException("The collection is empty");
        }
    }

    public int count() {
        return this.size;
    }

    private int count(Node<E> node) {
        if (node == null) {
            return 0;
        } else {
            return count(node.getLeft()) + count(node.getRight());
        }
    }

    public int rank(E element) {
        /*
        Node<E> currentNode = this.root;

        Queue<Node<E>> queue = new ArrayDeque<>();
        queue.offer(currentNode);

        int count = 0;
        while (!queue.isEmpty()) {
            currentNode = queue.poll();

            if (currentNode.getValue().compareTo(element) < 0) {
                count++;
            }

            if (currentNode.getLeft() != null) {
                queue.offer(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.offer(currentNode.getRight());
            }
        }

        return count;
        */

        return countOfSmallerElements(element, this.root);
    }

    private int countOfSmallerElements(E element, Node<E> node) {
        if (node == null) {
            return 0;
        }

        int count = 0;

        int compare = node.getValue().compareTo(element);

        if (compare < 0) {
            count = 1;
        }

        if (node.getRight() != null) {
            count += countOfSmallerElements(element, node.getRight());
        }

        if (node.getLeft() != null) {
            count += countOfSmallerElements(element, node.getLeft());
        }

        return count;
    }

    public E ceil(E element) {
        if (this.root == null) {
            return null;
        }

        Node<E> currentNode = this.root;
        E maxValue = null;

        while (true) {
            if (isGreaterThan(currentNode.getValue(), element)) {

                if (maxValue == null || isGreaterThan(maxValue, currentNode.getValue())) {
                    maxValue = currentNode.getValue();
                } else {
                    break;
                }

                if (currentNode.getLeft() != null) {
                    if (isGreaterThan(currentNode.getLeft().getValue(), element)) {
                        currentNode = currentNode.getLeft();
                    } else if (currentNode.getLeft().getRight() != null) {
                        currentNode = currentNode.getLeft().getRight();
                    }
                }

            } else if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
            } else {
                break;
            }
        }

        return maxValue;
    }

    public E floor(E element) {
        if (this.root == null) {
            return null;
        }

        Node<E> currentNode = this.root;
        E minValue = null;

        while (true) {
            if (isLessThan(currentNode.getValue(), element)) {

                if (minValue == null || isLessThan(minValue, currentNode.getValue())) {
                    minValue = currentNode.getValue();
                } else {
                    break;
                }

                if (currentNode.getRight() != null) {
                    if (isLessThan(currentNode.getRight().getValue(), element)) {
                        currentNode = currentNode.getRight();
                    } else if (currentNode.getRight().getLeft() != null) {
                        currentNode = currentNode.getRight().getLeft();
                    }
                }

            } else if (currentNode.getLeft() != null) {
                currentNode = currentNode.getLeft();
            } else {
                break;
            }
        }

        return minValue;
    }

    private boolean isGreaterThan(E first, E second) {
        return first.compareTo(second) > 0;
    }

    private boolean isLessThan(E first, E second) {
        return first.compareTo(second) < 0;
    }
}