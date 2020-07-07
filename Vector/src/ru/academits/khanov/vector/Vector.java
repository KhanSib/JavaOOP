package ru.academits.khanov.vector;

import java.util.Arrays;

public class Vector {
    private final double[] elements;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть отрецательной");
        }

        elements = new double[n];
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }

        elements = new double[vector.getSize()];

        for (int i = 0; i < vector.getSize(); i++) {
            setComponent(i, vector.getComponent(i));
        }
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }

        elements = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            setComponent(i, array[i]);
        }
    }

    public Vector(int dimension, double[] array) {
        if (dimension < 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть отрецательной");
        }

        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }

        elements = new double[dimension];

        for (int i = 0; i < dimension; i++) {
            if (i < array.length) {
                this.elements[i] = array[i];
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(elements).replace('[', '{').replace(']', '}');
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

        return (Arrays.equals(elements, vector.elements));
    }

    public int getSize() {
        return elements.length;
    }

    public Vector add(Vector vector) {
        if (this.elements == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }

        if (vector == null) {
            throw new IllegalArgumentException("Аргумент не может быть null");
        }

        Vector tempVector = new Vector(Math.max(getSize(), vector.getSize()));

        for (int i = 0; i < tempVector.getSize(); i++) {
            if (i < getSize()) {
                tempVector.elements[i] += getComponent(i);
            }

            if (i < vector.getSize()) {
                tempVector.elements[i] += vector.getComponent(i);
            }
        }

        return tempVector;
    }

    public Vector subtract(Vector vector) {
        if (this.elements == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }

        if (vector == null) {
            throw new IllegalArgumentException("Аргумент не может быть null");
        }

        Vector tempVector = new Vector(Math.max(getSize(), vector.getSize()));

        for (int i = 0; i < tempVector.getSize(); i++) {
            if (i < getSize()) {
                tempVector.elements[i] += getComponent(i);
            }

            if (i < vector.getSize()) {
                tempVector.elements[i] -= vector.getComponent(i);
            }
        }

        return tempVector;
    }

    public Vector numberMultiplication(double scalar) {
        for (int i = 0; i < getSize(); i++) {
            elements[i] *= scalar;
        }

        return this;
    }

    public Vector turn() {
        return this.numberMultiplication(-1);
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
            throw new ArrayIndexOutOfBoundsException("Индекс выходит за пределы размера вектора");
        }

        return elements[index];
    }

    public Vector setComponent(int index, double component) {
        if (index < 0 || index >= elements.length) {
            throw new ArrayIndexOutOfBoundsException("Индекс выходит за пределы размера вектора");
        }

        elements[index] = component;

        return this;
    }

    public static Vector sum(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }

        Vector tempVector1 = new Vector(vector1);
        Vector tempVector2 = new Vector(vector2);

        return new Vector(tempVector1.add(tempVector2));
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }

        Vector tempVector1 = new Vector(vector1);
        Vector tempVector2 = new Vector(vector2);

        return new Vector(tempVector1.subtract(tempVector2));
    }

    public static double getMultiplication(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }

        double vectorsMultiplication = 0;

        for (int i = 0; i < Math.min(vector1.getSize(), vector2.getSize()); i++) {
            vectorsMultiplication += vector1.elements[i] * vector2.elements[i];
        }

        return vectorsMultiplication;
    }
}