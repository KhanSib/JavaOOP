package ru.academits.khanov.tree;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    public void add(T value) {
        if (value == null) {
            throw new NullPointerException("Элемент для вставки не может быть null");
        }

        if (root == null) {
            root = new Node<>(value);
            return;
        }

        Node<T> current = root;
        Node<T> node = new Node<>(value);

        while (true) {
            if (node.compareTo(current) < 0) {
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } else {
                    current.setLeft(node);
                    return;
                }
            } else {
                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    current.setRight(node);
                    return;
                }
            }
        }
    }
}