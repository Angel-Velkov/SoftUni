package implementations;

import interfaces.AbstractTree;

import java.util.*;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    @SafeVarargs
    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.children = new ArrayList<>();
        addChildren(children);

    }

    @Override
    public List<E> orderBfs() {
        List<E> nodes = new ArrayList<>();

        Tree<E> currentNode = this;

        Queue<Tree<E>> treeQueue = new ArrayDeque<>();
        treeQueue.offer(this);

        while (!treeQueue.isEmpty()) {
            nodes.add(treeQueue.poll().getKey());

            for (Tree<E> child : currentNode.getChildren()) {
                treeQueue.offer(child);
            }
        }

        return nodes;
    }

    @Override
    public List<E> orderDfs() {
        List<E> nodes = new ArrayList<>();
        dfs(this, nodes);
        return nodes;
    }

    private void dfs(Tree<E> tree, List<E> nodes) {
        for (Tree<E> child : tree.getChildren()) {
            dfs(child, nodes);
        }
        nodes.add(tree.getKey());
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {

    }

    @Override
    public void removeNode(E nodeKey) {

    }

    @Override
    public void swap(E firstKey, E secondKey) {

    }

    public E getKey() {
        return this.key;
    }

    public List<Tree<E>> getChildren() {
        return this.children;
    }

    private void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    private void addChildren(Tree<E>[] children) {
        for (Tree<E> child : children) {
            this.children.add(child);
            child.setParent(this);
        }
    }

    private Tree<E> findNode(E nodeKey) {
        Tree<E> currentNode = this;

        Queue<Tree<E>> treeQueue = new ArrayDeque<>();
        treeQueue.offer(currentNode);

        while (!treeQueue.isEmpty()) {
            currentNode = treeQueue.poll();

            if (currentNode.getKey().equals(nodeKey)) {
                return currentNode;
            }

            for (Tree<E> child : currentNode.getChildren()) {
                treeQueue.offer(child);
            }
        }

        return null;
    }
}