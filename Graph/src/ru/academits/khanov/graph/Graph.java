package ru.academits.khanov.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

public class Graph {
    private int[][] graph;

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

        for (int j = 0; j < graph.length; j++) {
            if (!visited[j]) {
                queue.add(j);
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();

                if (!visited[current]) {
                    consumer.accept(current);

                    visited[current] = true;
                }

                for (int i = 0; i < graph.length; i++) {
                    if (graph[current][i] == 1 && current != i && !visited[i]) {
                        queue.add(i);
                    }
                }
            }
        }
    }

    public void visitNodesByDepth(Consumer<Integer> consumer) {
        boolean[] visited = new boolean[graph.length];
        Stack<Integer> stack = new Stack<>();

        for (int j = 0; j < graph.length; j++) {
            if (!visited[j]) {
                stack.push(j);
            }

            while (!stack.isEmpty()) {
                int current = stack.pop();

                if (!visited[current]) {
                    consumer.accept(current);

                    visited[current] = true;
                }

                for (int i = 0; i < graph.length; i++) {
                    if (graph[current][i] == 1 && current != i && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
    }
}