package ru.academits.khanov.vector;

import java.util.Arrays;

public class Vector {
    private double[] vector;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть отрецательной");
        }

        vector = new double[n];
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }

        this.vector = vector.getVector();
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }

        this.vector = array;
    }

    public Vector(int n, double[] array) {
        if (n < 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть отрецательной");
        }

        if (array == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }

        vector = new double[n];

        for (int i = 0; i < n; i++) {
            if (i < array.length) {
                this.vector[i] = array[i];
            } else {
                this.vector[i] = 0;
            }
        }
    }

    public double[] getVector() {
        return this.vector;
    }

    public void setVector(double[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }

        this.vector = vector;
    }

    @Override
    public String toString() {
        return Arrays.toString(vector);
    }

    @Override
    public int hashCode() {
        final int prime = 41;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(vector);
        return hash;
    }

    @Override
    public boolean equals(Object vector) {
        if (!(vector instanceof Vector)) {
            return false;
        }

        return (vector == this) || (this.vector == ((Vector) vector).vector);
    }

    public int getSize() {
        return vector.length;
    }

    public Vector add(Vector vector) {
        if (this.vector == null || vector == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }

        double[] tempArray = new double[Math.max(getSize(), vector.getSize())];

        for (int i = 0; i < tempArray.length; i++) {
            if (i < getSize()) {
                tempArray[i] += this.vector[i];
            }

            if (i < vector.getSize()) {
                tempArray[i] += vector.vector[i];
            }
        }

        this.setVector(tempArray);

        return this;
    }

    public Vector subtract(Vector vector) {
        if (this.vector == null || vector == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }

        double[] tempArray = new double[Math.max(getSize(), vector.getSize())];

        for (int i = 0; i < tempArray.length; i++) {
            if (i < getSize()) {
                tempArray[i] += this.vector[i];
            }
            if (i < vector.getSize()) {
                tempArray[i] -= vector.vector[i];
            }
        }

        this.setVector(tempArray);

        return this;
    }

    public Vector scalarMultiplication(double scalar) {
        for (int i = 0; i < getSize(); i++) {
            vector[i] *= scalar;
        }

        return this;
    }

    public Vector turn() {
        for (int i = 0; i < getSize(); i++) {
            vector[i] *= -1;
        }

        return this;
    }

    public double getLength() {
        double sum = 0;

        for (int i = 0; i < getSize(); i++) {
            sum += Math.pow(vector[i], 2);
        }

        return Math.sqrt(sum);
    }

    public Vector setComponent(int index, double component) {
        if (index < 0 || index >= vector.length) {
            throw new IllegalArgumentException("Индекс выходит за пределы размера вектора");
        }

        vector[index] = component;

        return this;
    }

    public double getComponent(int index) {
        if (index < 0 || index >= vector.length) {
            throw new IllegalArgumentException("Индекс выходит за пределы размера вектора");
        }

        return vector[index];
    }

    public static Vector sum(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }

        return new Vector(vector1.add(vector2));
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }

        return new Vector(vector1.add(vector2));
    }

    public static Vector multiplication(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("Вектор не может быть null");
        }

        double[] tempArray = new double[Math.max(vector1.getSize(), vector2.getSize())];

        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = 1;

            if (i < vector1.getSize()) {
                tempArray[i] *= vector1.vector[i];
            }

            if (i < vector2.getSize()) {
                tempArray[i] *= vector2.vector[i];
            }
        }

        return new Vector(tempArray);
    }
}