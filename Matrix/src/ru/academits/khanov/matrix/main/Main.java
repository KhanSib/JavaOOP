package ru.academits.khanov.matrix.main;

import ru.academits.khanov.matrix.Matrix;
import ru.academits.khanov.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(10, 2);

        System.out.println(matrix1);

        Matrix matrix3 = new Matrix(new double[][]{{1, 2, 3}, {1, 3}});

        System.out.println(matrix3);

        Vector[] vectors = {new Vector(new double[]{1, 2, 3}), new Vector(new double[]{3, 4, 5, 6, 7})};
        Matrix matrix4 = new Matrix(vectors);

        System.out.println(matrix4);

        System.out.println(Arrays.toString(matrix4.getSize()));

        Vector vector1 = new Vector(matrix4.getLineVector(1));

        System.out.println(matrix4.getLineVector(1));


        Vector vector2 = new Vector(new double[]{1, 2});
        matrix4.setLineVector(0, vector2);
        System.out.println(matrix4);

        System.out.println(matrix4.getColumnVector(1));

        System.out.println(matrix4.getTransposeMatrix());

        System.out.println(matrix4.increaseByScalar(4));

        System.out.println(matrix4.multiplyByVector(new Vector(new double[]{1, 2, 3, 4})));

        Matrix matrix5 = new Matrix(new double[][]{{1, 2, 3}, {1, 3},{1,2,3,4,5},{1}});
        Matrix matrix6 = new Matrix(new double[][]{{1, 2, 3}, {1, 3}});
        System.out.println(matrix5.add(matrix6));
        System.out.println(matrix5.subtract(matrix6));
    }
}
