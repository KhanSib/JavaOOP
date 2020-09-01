package ru.academits.khanov.tree.main;

import ru.academits.khanov.tree.BinaryTree;

import java.util.Comparator;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree1 = new BinaryTree<>();
        binaryTree1.add(0);
        binaryTree1.add(-2);
        binaryTree1.add(2);
        binaryTree1.add(-4);
        binaryTree1.add(-1);
        binaryTree1.add(1);
        binaryTree1.add(4);
        binaryTree1.add(1);
        binaryTree1.add(null);

        System.out.println("Дерево 1: ");
        binaryTree1.visitNodesByDepthRecursion(integer -> System.out.print(integer + " "));

        System.out.println();
        System.out.println("Кол-во элементов дерева 1: " + binaryTree1.size());

        binaryTree1.remove(-2);
        binaryTree1.remove(0);
        binaryTree1.remove(-1);
        binaryTree1.remove(4);
        binaryTree1.remove(-4);
        binaryTree1.remove(2);

        System.out.println("Дерево 1, после удаления -2, 0, -1, 4, -4, 2: ");
        binaryTree1.visitNodesByDepthRecursion(integer -> System.out.print(integer + " "));

        System.out.println();
        System.out.println("Кол-во элементов дерева 1: " + binaryTree1.size());

        binaryTree1.remove(1);
        binaryTree1.remove(1);

        System.out.println("Дерево 1, после удаления 1, 1: ");
        binaryTree1.visitNodesByDepthRecursion(integer -> System.out.print(integer + " "));

        System.out.println();
        System.out.println("Кол-во элементов дерева 1: " + binaryTree1.size());

        BinaryTree<Integer> binaryTree2 = new BinaryTree<>(Comparator.comparingInt(Integer::intValue));

        binaryTree2.add(8);
        binaryTree2.add(3);
        binaryTree2.add(12);
        binaryTree2.add(1);
        binaryTree2.add(6);
        binaryTree2.add(14);
        binaryTree2.add(4);
        binaryTree2.add(7);
        binaryTree2.add(13);
        binaryTree2.add(9);
        binaryTree2.add(90);
        binaryTree2.add(100);

        System.out.println("Дерево 2: ");
        binaryTree2.visitNodesByDepthRecursion(integer -> System.out.print(integer + " "));
        System.out.println();

        System.out.println("Проверка наличия значения 13: " + binaryTree2.contains(13));
        System.out.println("Удаления элемента по значению 8: " + binaryTree2.remove(8));

        System.out.println("Кол-во элементов дерева: " + binaryTree2.size());

        Consumer<Integer> consumer = integer -> System.out.print(integer + " ");

        System.out.println("Проход дерева в ширину:");
        binaryTree2.visitNodesByWidth(consumer);
        System.out.println();

        System.out.println("Проход дерева в глубину:");
        binaryTree2.visitNodesByDepth(consumer);
        System.out.println();

        System.out.println("Проход дерева в глубину с рекурсией:");
        binaryTree2.visitNodesByDepthRecursion(consumer);
    }
}