package ru.academits.khanov.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    public Node<T> getRoot(){
        return root;
    }

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
            if (node.getValue().compareTo(current.getValue()) < 0) {
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

    public boolean isContains(T value) {
        if (value == null) {
            throw new NullPointerException("Элемент для поиска не может быть null");
        }

        if (root == null) {
            root = new Node<>(value);
            return false;
        }

        Node<T> current = root;

        while (true) {
            if (value.compareTo(current.getValue()) == 0) {
                return true;
            }

            if (value.compareTo(current.getValue()) < 0) {
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } else {
                    return false;
                }
            } else {
                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    return false;
                }
            }
        }
    }

    public boolean remove(T value) {
        if (value == null) {
            throw new NullPointerException("Элемент для удаления не может быть null");
        }

        if (root == null) {
            root = new Node<>(value);
            return false;
        }

        Node<T> previous = new Node<>();
        Node<T> current = root;
        int diraction = 0;

        while (true) {
            if (value.compareTo(current.getValue()) == 0) {
                if (current.getLeft() == null && current.getRight() == null) {
                    if (diraction == -1) {
                        previous.setLeft(null);
                    } else {
                        previous.setRight(null);
                    }

                    return true;
                }

                if (current.getLeft() == null) {
                    if (diraction == -1) {
                        previous.setLeft(current.getRight());
                    } else {
                        previous.setRight(current.getRight());
                    }

                    return true;
                }

                if (current.getRight() == null) {
                    if (diraction == -1) {
                        previous.setLeft(current.getLeft());
                    } else {
                        previous.setRight(current.getLeft());
                    }

                    return true;
                }

                Node<T> previousMinNodeRightSubtree = new Node<>();
                Node<T> minNodeRightSubtree = current;

                while (minNodeRightSubtree.getLeft() != null) {
                    previousMinNodeRightSubtree = minNodeRightSubtree;
                    minNodeRightSubtree = minNodeRightSubtree.getLeft();
                }

                if (minNodeRightSubtree.getRight() != null) {
                    previousMinNodeRightSubtree.setLeft(minNodeRightSubtree.getRight());
                } else {
                    previousMinNodeRightSubtree.setLeft(null);
                }

                if (diraction == -1) {
                    previous.setLeft(minNodeRightSubtree);
                } else {
                    previous.setRight(minNodeRightSubtree);
                }

                minNodeRightSubtree.setLeft(current.getLeft());
                minNodeRightSubtree.setRight(current.getRight());

                if (diraction == 0) {
                    previous.setValue(minNodeRightSubtree.getValue());
                    previous.setLeft(root.getLeft());
                    previous.setRight(root.getRight());
                    root = previous;
                }

                return true;
            }

            if (value.compareTo(current.getValue()) < 0) {
                if (current.getLeft() != null) {
                    previous = current;
                    current = current.getLeft();
                    diraction = -1;
                } else {
                    return false;
                }
            } else {
                if (current.getRight() != null) {
                    previous = current;
                    current = current.getRight();
                    diraction = 1;
                } else {
                    return false;
                }
            }
        }
    }

    public int getNodesCount() {
        if (root == null) {
            return 0;
        }

        Stack<Node<T>> stack = new Stack<>();

        stack.push(root);
        int count = 1;

        while (!stack.empty()) {
            Node<T> current = stack.pop();
            count++;

            if (current.getLeft() != null) {
                stack.push(current.getLeft());
            }

            if (current.getRight() != null) {
                stack.push(current.getRight());
            }
        }

        return count;
    }

    public void visitNodesByWidth(Consumer<T> consumer) {
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
        Stack<Node<T>> stack = new Stack<>();

        stack.push(root);

        while (!stack.empty()) {
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

    public void visitNodesByDepthRecursion(Node<T> node, Consumer<T> consumer) {
        consumer.accept(node.getValue());

        if (node.getLeft() != null) {
            visitNodesByDepthRecursion(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            visitNodesByDepthRecursion(node.getRight(), consumer);
        }
    }
}
