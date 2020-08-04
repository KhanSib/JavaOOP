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
}