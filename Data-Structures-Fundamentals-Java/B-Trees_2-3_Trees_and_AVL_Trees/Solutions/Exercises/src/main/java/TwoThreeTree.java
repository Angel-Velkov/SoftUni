public class TwoThreeTree<K extends Comparable<K>> {
    private TreeNode<K> root;

    public static class TreeNode<K> {
        private K leftKey;
        private K rightKey;

        private TreeNode<K> leftChild;
        private TreeNode<K> middleChild;
        private TreeNode<K> rightChild;

        private TreeNode(K key) {
            this.leftKey = key;
        }

        boolean isThreeNode() {
            return rightKey != null;
        }

        boolean isTwoNode() {
            return rightKey == null;
        }

        boolean isLeaf() {
            return this.leftChild == null && this.middleChild == null && this.rightChild == null;
        }
    }

    public void insert(K key) {

    }

    private void insert(K key, TreeNode<K> node) {

    }

    public String getAsString() {
        StringBuilder out = new StringBuilder();
        recursivePrint(this.root, out);
        return out.toString().trim();
    }

    private void recursivePrint(TreeNode<K> node, StringBuilder out) {
        if (node == null) {
            return;
        }
        if (node.leftKey != null) {
            out.append(node.leftKey)
                    .append(" ");
        }
        if (node.rightKey != null) {
            out.append(node.rightKey).append(System.lineSeparator());
        } else {
            out.append(System.lineSeparator());
        }
        if (node.isTwoNode()) {
            recursivePrint(node.leftChild, out);
            recursivePrint(node.rightChild, out);
        } else if (node.isThreeNode()) {
            recursivePrint(node.leftChild, out);
            recursivePrint(node.middleChild, out);
            recursivePrint(node.rightChild, out);
        }
    }

    private TreeNode<K> findNode(K key) {
        if (this.root == null) {
            return null;
        }

        return findNode(key, this.root);
    }

    private TreeNode<K> findNode(K key, TreeNode<K> node) {
        if (node.isLeaf()) {
            return node;
        }

        if (isLessThan(key, node.leftKey)) {
            return findNode(key, node.leftChild);
        } else if (node.rightKey != null && isLessThan(node.rightKey, key)) {
            return findNode(key, node.rightChild);
        } else {
            return findNode(key, node.middleChild);
        }
    }

    private boolean isLessThan(K a, K b) {
        return a.compareTo(b) < 0;
    }

    private boolean isGreaterThan(K a, K b) {
        return a.compareTo(b) > 0;
    }

    private boolean isBetween(K lowerBound, K element, K upperBound) {
        return isGreaterThan(element, lowerBound) && isLessThan(element, upperBound)
                ||isLessThan(element, lowerBound) && isGreaterThan(element, upperBound);
    }
}
