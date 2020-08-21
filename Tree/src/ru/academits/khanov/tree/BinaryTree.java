package ru.academits.khanov.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinaryTree<T> {
    private Node<T> root;
    private final Comparator<T> comparator;
    private int count;

    public BinaryTree() {
        comparator = (Comparator<T>) Comparator.naturalOrder();
    }

    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void add(T value) {
        if (root == null) {
            root = new Node<>(value);
            return;
        }

        Node<T> current = root;
        Node<T> node = new Node<>(value);

        while (true) {
            if (comparator.compare(node.getValue(), current.getValue()) < 0) {
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } else {
                    current.setLeft(node);
                    count++;
                    return;
                }
            } else {
                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    current.setRight(node);
                    count++;
                    return;
                }
            }
        }
    }

    private Node<T> getPreviousNodeOfRequiredValue(T value) {
        if (root == null) {
            return null;
        }

        Node<T> previous = new Node<>(null);
        previous.setLeft(root);
        Node<T> current = root;

        while (true) {
            int resultOfComparing = comparator.compare(value, current.getValue());

            if (resultOfComparing == 0) {
                return previous;
            }

            if (resultOfComparing < 0) {
                if (current.getLeft() != null) {
                    previous = current;
                    current = current.getLeft();
                } else {
                    return null;
                }
            } else {
                if (current.getRight() != null) {
                    previous = current;
                    current = current.getRight();
                } else {
                    return null;
                }
            }
        }
    }

    public boolean contains(T value) {
        return getPreviousNodeOfRequiredValue(value) != null;
    }

    public boolean remove(T value) {
        if (root == null) {
            return false;
        }

        Node<T> previous = getPreviousNodeOfRequiredValue(value);

        if (previous == null) {
            return false;
        }

        Node<T> current = previous.getLeft();
        boolean isLeftDirection = true;

        if (comparator.compare(value, current.getValue()) != 0) {
            current = previous.getRight();
            isLeftDirection = false;
        }

        if (current.getLeft() == null && current.getRight() == null) {
            if (isLeftDirection) {
                previous.setLeft(null);
            } else {
                previous.setRight(null);
            }

            count--;
            return true;
        }

        if (current.getLeft() == null) {
            if (isLeftDirection) {
                previous.setLeft(current.getRight());
            } else {
                previous.setRight(current.getRight());
            }

            count--;
            return true;
        }

        if (current.getRight() == null) {
            if (isLeftDirection) {
                previous.setLeft(current.getLeft());
            } else {
                previous.setRight(current.getLeft());
            }

            count--;
            return true;
        }

        Node<T> previousMinNodeRightSubtree = current;
        Node<T> minNodeRightSubtree = current.getRight();

        while (minNodeRightSubtree.getLeft() != null) {
            previousMinNodeRightSubtree = minNodeRightSubtree;
            minNodeRightSubtree = minNodeRightSubtree.getLeft();
        }

        if (minNodeRightSubtree.getRight() != null) {
            previousMinNodeRightSubtree.setLeft(minNodeRightSubtree.getRight());
        } else {
            previousMinNodeRightSubtree.setLeft(null);
        }

        if (isLeftDirection) {
            previous.setLeft(minNodeRightSubtree);
        } else {
            previous.setRight(minNodeRightSubtree);
        }

        minNodeRightSubtree.setLeft(current.getLeft());
        minNodeRightSubtree.setRight(current.getRight());

        count--;
        return true;
    }

    public int size() {
        return count;
    }

    public void visitNodesByWidth(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Queue<Node<T>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            consumer.accept(current.getValue());

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }

            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
    }

    public void visitNodesByDepth(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        LinkedList<Node<T>> stack = new LinkedList<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> current = stack.pop();
            consumer.accept(current.getValue());

            if (current.getRight() != null) {
                stack.push(current.getRight());
            }

            if (current.getLeft() != null) {
                stack.push(current.getLeft());
            }
        }
    }

    public void visitNodesByDepthRecursion(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        visitNodesByDepthRecursionWithNode(root, consumer);
    }

    private void visitNodesByDepthRecursionWithNode(Node<T> node, Consumer<T> consumer) {
        consumer.accept(node.getValue());

        if (node.getLeft() != null) {
            visitNodesByDepthRecursionWithNode(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            visitNodesByDepthRecursionWithNode(node.getRight(), consumer);
        }
    }
}