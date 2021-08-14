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

    public void eachInOrder(Consumer<E> consumer, Node<E> node) {
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

        return new BinarySearchTree<>(copy(node));
    }

    private Node<E> copy(Node<E> node) {
        if (node == null) {
            return null;
        }

        Node<E> newNode = new Node<>(node.getValue());
        newNode.setLeftChild(copy(node.getLeft()));
        newNode.setRightChild(copy(node.getRight()));

        return newNode;
    }

    // We can use the eachInOrder method and submit the list to fill it out.
    public List<E> range(E first, E second) {
        List<E> elements = new ArrayList<>();
        fillInOrder(elements, root, first, second);

        return elements;
    }

    private void fillInOrder(List<E> list, Node<E> parent, E from, E to) {
        if (parent == null || from.compareTo(to) > 0) {
            return;
        }

        Node<E> leftChild = parent.getLeft();
        if (leftChild != null) {
            if (isBetween(leftChild, from, to)) {
                fillInOrder(list, leftChild, from, to);
            }
        }

        list.add(parent.getValue());

        Node<E> rightChild = parent.getRight();
        if (rightChild != null) {
            if (isBetween(rightChild, from, to)) {
                fillInOrder(list, rightChild, from, to);
            }
        }
    }

    private boolean isBetween(Node<E> node, E from, E to) {
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
        if (count() == 0) {
            throw new IllegalStateException("The collection is empty");
        }
    }

    public int count() {
        return this.size;
    }

    public int rank(E element) {
        /*
        Node<E> currentNode = this.search(element).getRoot();

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
            count++;
        }

        if (node.getRight() != null && node.getRight().getValue().compareTo(element) < 0) {
            count += countOfSmallerElements(element, node.getRight());
        }

        if (node.getLeft() != null) {
            count += countOfSmallerElements(element, node.getLeft());
        }

        return count;
    }

    // Todo: Need to be fixed
    public E ceil(E element) {
        ensureNonEmpty();

        Node<E> currentNode = this.root;

        while (true) {
            if (isGreaterThan(currentNode.getValue(), element)) {
                if (currentNode.getLeft() != null) {
                    if (isGreaterThan(currentNode.getLeft().getValue(), element)) {
                        currentNode = currentNode.getLeft();
                    } else if (currentNode.getLeft().getRight() != null) {
                        currentNode = currentNode.getLeft().getRight();
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } else if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
            } else {
                return null;
            }
        }

        return currentNode.getValue();
    }

    public E floor(E element) {
        return null;
    }

    private boolean isGreaterThan(E first, E second) {
        return first.compareTo(second) > 0;
    }


}