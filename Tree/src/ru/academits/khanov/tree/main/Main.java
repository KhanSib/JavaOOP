package ru.academits.khanov.tree.main;

import ru.academits.khanov.tree.BinaryTree;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();

        binaryTree.add(8);
        binaryTree.add(3);
        binaryTree.add(12);
        binaryTree.add(1);
        binaryTree.add(6);
        binaryTree.add(14);
        binaryTree.add(4);
        binaryTree.add(7);
        binaryTree.add(13);
        binaryTree.add(9);
        binaryTree.add(90);
        binaryTree.add(100);

        System.out.println("Проверка наличия значения 13: " + binaryTree.isContains(13));
        System.out.println("Удаления элемента по значению 8: " + binaryTree.remove(8));

        System.out.println("Кол-во элементов дерева: " + binaryTree.getNodesCount());

        Consumer<Integer> consumer = integer -> System.out.print(integer + " ");

        System.out.println("Проход дерева в ширину:");
        binaryTree.visitNodesByWidth(consumer);
        System.out.println();

        System.out.println("Проход дерева в глубину:");
        binaryTree.visitNodesByDepth(consumer);
        System.out.println();

        System.out.println("Проход дерева в глубину с рекурсией:");
        binaryTree.visitNodesByDepthRecursion(binaryTree.getRoot(), consumer);
    }
}