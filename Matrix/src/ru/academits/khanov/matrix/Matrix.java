package ru.academits.khanov.matrix;

import ru.academits.khanov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private final Vector[] vectors;

    public Matrix(int n, int m) {
        vectors = new Vector[m];

        for (int i = 0; i < vectors.length; i++) {
            vectors[i] = new Vector(n);
        }
    }

    public Matrix(Matrix matrix) {
        vectors = new Vector[matrix.vectors.length];

        for (int i = 0; i < matrix.vectors.length; i++) {
            vectors[i] = new Vector(matrix.vectors[i]);
        }
    }

    public Matrix(double[][] array) {
        vectors = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            vectors[i] = new Vector(array[i]);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(vectors).replace('[', '{').replace(']', '}');
    }

}
