package ru.academits.khanov.graph.main;

import ru.academits.khanov.graph.Graph;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(new int[][]{
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        });

        Consumer<Integer> consumer = integer -> System.out.print(integer + " ");

        System.out.println("Проход в ширину");
        graph.visitNodesByWidth(consumer);

        System.out.println();
        System.out.println("Проход в глубину");
        graph.visitNodesByDepth(consumer);

        System.out.println();
        System.out.println("Проход в глубину с рекурсией");
        graph.visitNodesByDepthRecursion(consumer);
    }
}