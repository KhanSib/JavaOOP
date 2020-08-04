package ru.academits.khanov.tree;

public class Node<T> {
    private Node<T> left;
    private Node<T> right;
    private T value;

    public Node() {
    }

    public Node(T value) {
        if (value == null) {
            throw new NullPointerException("Значение не может быть null");
        }

        this.value = value;
    }

    public Node(T value, Node<T> left, Node<T> right) {
        if (value == null) {
            throw new NullPointerException("Значение не может быть null");
        }

        this.value = value;
        this.left = left;
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "{" + value + "}";
    }
}