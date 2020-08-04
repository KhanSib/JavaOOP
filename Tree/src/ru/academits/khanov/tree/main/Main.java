package ru.academits.khanov.tree.main;

import ru.academits.khanov.tree.BinaryTree;

public class Main {
    public static void main(String[] args){
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

        System.out.println(binaryTree.isContains(13));
        System.out.println(binaryTree.remove(8));

        binaryTree.add(300);
    }
}
