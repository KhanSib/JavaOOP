package ru.academits.khanov.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Graph {
    private final int[][] graph;

    public Graph(int[][] array) {
        if (array == null) {
            throw new NullPointerException("Матрица не может быть null");
        }

        if (array.length != array[0].length) {
            throw new IndexOutOfBoundsException("Матрица должна быть квадратной");
        }

        graph = array;
    }

    public void visitNodesByWidth(Consumer<Integer> consumer) {
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                queue.add(i);
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();

                if (!visited[current]) {
                    consumer.accept(current);

                    visited[current] = true;

                    for (int j = 0; j < graph.length; j++) {
                        if (graph[current][j] == 1 && !visited[j]) {
                            queue.add(j);
                        }
                    }
                }
            }
        }
    }

    public void visitNodesByDepth(Consumer<Integer> consumer) {
        boolean[] visited = new boolean[graph.length];
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                stack.push(i);
            }

            while (!stack.isEmpty()) {
                int current = stack.pop();

                if (!visited[current]) {
                    consumer.accept(current);
                    visited[current] = true;

                    for (int j = graph.length - 1; j >= 0; j--) {
                        if (graph[current][j] == 1 && !visited[j]) {
                            stack.push(j);
                        }
                    }
                }
            }
        }
    }

    public void visitNodesByDepthRecursion(Consumer<Integer> consumer) {
        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                visitNodesByDepthRecursionWithNode(i, consumer, visited);
            }
        }
    }

    private void visitNodesByDepthRecursionWithNode(int index, Consumer<Integer> consumer, boolean[] visited) {
        consumer.accept(index);
        visited[index] = true;

        for (int i = 1; i < graph.length; i++) {
            if (graph[index][i] == 1 && !visited[i]) {
                visitNodesByDepthRecursionWithNode(i, consumer, visited);
            }
        }
    }
}