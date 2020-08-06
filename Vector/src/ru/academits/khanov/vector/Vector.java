package ru.academits.khanov.vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть нилевой или отрицательной." +
                    System.lineSeparator() + "Размерность = " + dimension);
        }

        elements = new double[dimension];
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }

        elements = Arrays.copyOf(vector.elements, vector.elements.length);
    }

    public Vector(int dimension, Vector vector) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть нилевой или отрицательной." +
                    System.lineSeparator() + "Размерность = " + dimension);
        }

        if (vector == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }

        elements = Arrays.copyOf(vector.elements, dimension);
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустым");
        }

        elements = Arrays.copyOf(array, array.length);
    }

    public Vector(int dimension, double[] array) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть нилевой или отрицательной." +
                    System.lineSeparator() + "Размерность = " + dimension);
        }

        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }

        elements = Arrays.copyOf(array, dimension);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('{');

        for (int i = 0; i < elements.length - 1; i++) {
            stringBuilder.append(elements[i]);
            stringBuilder.append(", ");
        }

        stringBuilder.append(elements[elements.length - 1]);
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 41;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(elements);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) obj;

        return Arrays.equals(elements, vector.elements);
    }

    public int getSize() {
        return elements.length;
    }

    public Vector add(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Аргумент не может быть null");
        }

        if (elements.length < vector.elements.length) {
            elements = Arrays.copyOf(elements, vector.elements.length);
        }

        for (int i = 0; i < vector.elements.length; i++) {
            elements[i] += vector.elements[i];
        }

        return this;
    }

    public Vector subtract(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Аргумент не может быть null");
        }

        if (elements.length < vector.elements.length) {
            elements = Arrays.copyOf(elements, vector.elements.length);
        }

        for (int i = 0; i < vector.elements.length; i++) {
            elements[i] -= vector.elements[i];
        }

        return this;
    }

    public Vector multiplyByScalar(double scalar) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= scalar;
        }

        return this;
    }

    public Vector turn() {
        return multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double element : elements) {
            sum += Math.pow(element, 2);
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера вектора" +
                    System.lineSeparator() + "Индекс = " + index);
        }

        return elements[index];
    }

    public Vector setComponent(int index, double component) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера вектора" +
                    System.lineSeparator() + "Индекс = " + index);
        }

        elements[index] = component;

        return this;
    }

    public static Vector sum(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("Первый аргумент null, вектор не может быть null");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("Второй аргумент null, вектор не может быть null");
        }

        Vector vector = new Vector(vector1);

        return vector.add(vector2);
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("Первый аргумент null, вектор не может быть null");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("Второй аргумент null, вектор не может быть null");
        }

        Vector vector = new Vector(vector1);

        return vector.subtract(vector2);
    }

    public static double getVectorsProduct(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("Первый аргумент null, вектор не может быть null");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("Второй аргумент null, вектор не может быть null");
        }

        double vectorsProduct = 0;

        int minVectorsLength = Math.min(vector1.elements.length, vector2.elements.length);

        for (int i = 0; i < minVectorsLength; i++) {
            vectorsProduct += vector1.elements[i] * vector2.elements[i];
        }

        return vectorsProduct;
    }
}