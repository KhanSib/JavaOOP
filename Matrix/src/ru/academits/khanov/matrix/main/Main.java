package ru.academits.khanov.matrix.main;

import ru.academits.khanov.matrix.Matrix;
import ru.academits.khanov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(10, 2);

        System.out.println("Матрица 1: " + matrix1);

        Matrix matrix3 = new Matrix(new double[][]{{1, 2, 3}, {1, 3}});

        System.out.println("Матрица 3: " + matrix3);

        Vector[] vectors = {new Vector(new double[]{1, 2, 3}), new Vector(new double[]{3, 4, 5, 6, 7})};
        Matrix matrix4 = new Matrix(vectors);

        System.out.println("Матрица 4: " + matrix4);

        System.out.println("Кол-во столбцов матрицы 4: " + matrix4.getColumnCount());
        System.out.println("Кол-во строк матрицы 4: " + matrix4.getRowCount());

        Vector vector1 = new Vector(matrix4.getRow(1));

        System.out.println("Вторая строка матрицы 4: " + matrix4.getRow(1));

        Vector vector2 = new Vector(new double[]{1, 2, 0, 0, 0});
        matrix4.setRow(0, vector2);

        System.out.println("Матрица 4: " + matrix4);

        System.out.println("Второй столбец матрицы 4: " + matrix4.getColumn(1));

        System.out.println("Транспонирование матрицы 4: " + matrix4.transposeMatrix());

        System.out.println("Умножение на скаляр 4 матрицы 4: " + matrix4.multiplyByScalar(4));

        System.out.println("Умножение матрицы 4 на вектор {1, 2}: " + matrix4.multiplyByVector(new Vector(new double[]{1, 2})));

        Matrix matrix5 = new Matrix(new double[][]{{1, 2, 3}, {1, 3}, {1, 2, 3, 4, 5}, {1}});
        Matrix matrix6 = new Matrix(new double[][]{{1, 2, 3}, {1, 3}, {0}, {1, 6, 8, 9, 0}});

        System.out.println("Матрица 5 + Матрица 6: " + matrix5.add(matrix6));
        System.out.println("Матрица 5 + Матрица 6: " + matrix5.subtract(matrix6));

        Matrix matrix7 = new Matrix(new double[][]{
                {-4, -2, -7, 8},
                {2, 7},
                {2, 0, 6, -3},
                {6, 4, -10}});
        System.out.println("Определитель матрицы 7: " + matrix7.getDeterminant());

        Matrix matrix8 = new Matrix(new double[][]{
                {-4, -2, -7, 8},
                {2, 7},
                {2, 0, 6, -3},
                {6, 4, -10}});

        Matrix matrix9 = new Matrix(new double[][]{
                {-4, -2, -7, 8},
                {2, 7},
                {2, 0, 6, -3},
                {6, 4, -10, 1}});

        System.out.println("Произведение матриц 8 и 9: " + Matrix.getProduct(matrix8, matrix9));
    }
}