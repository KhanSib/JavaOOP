package ru.academits.khanov.vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть отрицательной");
        }

        elements = new double[dimension];
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }

        elements = new double[vector.elements.length];

        System.arraycopy(vector.elements, 0, elements, 0, vector.elements.length);
    }

    public Vector(double[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть null или пустым");
        }

        elements = new double[array.length];

        System.arraycopy(array, 0, elements, 0, array.length);
    }

    public Vector(int dimension, double[] array) {
        if (dimension < 1) {
            throw new IllegalArgumentException("Размерность вектора не может быть отрицательной или нулевой");
        }

        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть null или пустым");
        }

        elements = new double[dimension];

        System.arraycopy(array, 0, elements, 0, array.length);
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
            double[] tempElements = elements;
            elements = new double[vector.elements.length];

            System.arraycopy(tempElements, 0, elements, 0, tempElements.length);
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
            double[] tempElements = elements;
            elements = new double[vector.elements.length];

            System.arraycopy(tempElements, 0, elements, 0, tempElements.length);
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
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера вектора");
        }

        return elements[index];
    }

    public Vector setComponent(int index, double component) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера вектора");
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

        Vector vector = new Vector(Math.max(vector1.elements.length, vector2.elements.length));

        System.arraycopy(vector1.elements, 0, vector.elements, 0, vector1.elements.length);

        for (int i = 0; i < vector2.elements.length; i++) {
            vector.elements[i] += vector2.elements[i];

        }

        return vector;
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("Первый аргумент null, вектор не может быть null");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("Второй аргумент null, вектор не может быть null");
        }

        Vector vector = new Vector(Math.max(vector1.elements.length, vector2.elements.length));

        System.arraycopy(vector1.elements, 0, vector.elements, 0, vector1.elements.length);

        for (int i = 0; i < vector2.elements.length; i++) {
            vector.elements[i] -= vector2.elements[i];

        }

        return vector;
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