package ru.academits.khanov.matrix;

import ru.academits.khanov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int columnsCount, int rowsCount) {
        if (columnsCount <= 0) {
            throw new IndexOutOfBoundsException("Количество столбцов не может быть нуливым или отрицательным");
        }

        if (rowsCount <= 0) {
            throw new IndexOutOfBoundsException("Количество строк не может быть нуливым или отрицательным");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Матрица не может быть null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Матрица не может быть 0 размерности");
        }

        for (double[] row : array) {
            if (row == null) {
                throw new NullPointerException("Строка матрицы не может быть null");
            }
        }

        for (double[] doubles : array) {
            if (doubles.length != 0) {
                break;
            }

            throw new IllegalArgumentException("Матрица не может быть 0 размерности");
        }


        rows = new Vector[array.length];

        int maxLength = 0;

        for (double[] doubles : array) {
            if (maxLength < doubles.length) {
                maxLength = doubles.length;
            }
        }

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxLength, array[i]);
        }
    }

    public Matrix(Vector[] rows) {
        if (rows == null) {
            throw new NullPointerException("Массив векторов не может быть null");
        }

        if (rows.length == 0) {
            throw new IllegalArgumentException("Матрица не может быть 0 размерности");
        }

        for (Vector vector : rows) {
            if (vector == null) {
                throw new NullPointerException("Ни один из векторов не может быть null");
            }
        }

        for (Vector vector : rows) {
            if (vector.getSize() != 0) {
                break;
            }

            throw new IllegalArgumentException("Матрица не может быть 0 размерности");
        }

        int maxLength = 0;

        for (Vector elements : rows) {
            if (maxLength < elements.getSize()) {
                maxLength = elements.getSize();
            }
        }

        this.rows = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            this.rows[i] = new Vector(maxLength).add(rows[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('{');

        for (int i = 0; i < rows.length - 1; i++) {
            stringBuilder.append(rows[i]);
            stringBuilder.append(", ");
        }

        stringBuilder.append(rows[rows.length - 1]);
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public int getRowsCount() {
        return rows.length;
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера матрицы");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера матрицы");
        }

        if (vector == null) {
            throw new NullPointerException("Вектор не может быть null");
        }

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Длина вектора не соответсвует размеру строк матрицы");
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера матрицы");
        }

        Vector vector = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            vector.setComponent(i, rows[i].getComponent(index));
        }

        return vector;
    }

    public Matrix transposeMatrix() {
        int columnsCount = getColumnsCount();
        int rowsCount = getRowsCount();

        if (rowsCount < columnsCount) {
            rows = Arrays.copyOf(rows, columnsCount);

            for (int i = rowsCount; i < columnsCount; i++) {
                rows[i] = new Vector(columnsCount);
            }
        } else {
            for (int i = 0; i < rowsCount; i++) {
                rows[i] = new Vector(rowsCount, rows[i]);
            }
        }

        for (int i = 0; i < getRowsCount(); i++) {
            Vector tempColumn = getColumn(i);

            for (int j = i + 1; j < getRowsCount(); j++) {
                rows[j].setComponent(i, rows[i].getComponent(j));
                rows[i].setComponent(j, tempColumn.getComponent(j));
            }
        }

        return this;
    }

    public Matrix multiplyByScalar(double scalar) {
        for (Vector vector : rows) {
            vector.multiplyByScalar(scalar);
        }

        return this;
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не может быть null");
        }

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Длина вектора не соответсвует длине строки матрицы");
        }

        Vector outVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            outVector.setComponent(i, Vector.getVectorsProduct(vector, rows[i]));
        }

        return outVector;
    }

    public Matrix add(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Аргумент не может быть null");
        }

        if (getRowsCount() != matrix.getRowsCount()) {
            throw new IllegalArgumentException("Кол-во строк матрицы аргумента не равно кол-ву строк матрицы");
        }

        if (getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Кол-во столбцов матрицы аргумента не равно кол-ву столбцов матрицы");
        }

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }

        return this;
    }

    public Matrix subtract(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Аргумент не может быть null");
        }

        if (getRowsCount() != matrix.getRowsCount()) {
            throw new IllegalArgumentException("Кол-во строк матрицы аргумента не равно кол-ву строк матрицы");
        }

        if (getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Кол-во столбцов матрицы аргумента не равно кол-ву столбцов матрицы");
        }

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i].subtract(matrix.rows[i]);
        }

        return this;
    }

    public double getDeterminant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }

        int rowsCount = getRowsCount();
        double epsilon = 1.0e-10;
        double[][] triangularMatrix = new double[rowsCount][rowsCount];

        for (int row = 0; row < rowsCount; row++) {
            for (int column = 0; column < rowsCount; column++) {
                triangularMatrix[row][column] = rows[row].getComponent(column);
            }
        }

        for (int i = 0; i < rowsCount - 1; i++) {
            for (int j = i + 1; j < rowsCount; j++) {
                if (Math.abs(triangularMatrix[i][i]) < epsilon) {
                    continue;
                }

                double reductionCoefficient = triangularMatrix[i][j] / triangularMatrix[i][i];

                for (int k = i; k < rowsCount; k++) {
                    triangularMatrix[k][j] -= reductionCoefficient * triangularMatrix[k][i];
                }
            }
        }

        double determinant = 1;

        for (int i = 0; i < rowsCount; i++) {
            determinant *= triangularMatrix[i][i];
        }

        return determinant;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("Первый аргумент null, матрица не может быть null");
        }

        if (matrix2 == null) {
            throw new NullPointerException("Второй аргумент null, матрица не может быть null");
        }

        if (matrix1.getRowsCount() != matrix2.getRowsCount() && matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Размерность матриц не совпадает");
        }

        Matrix matrix = new Matrix(matrix1);

        return matrix.add(matrix2);
    }

    public static Matrix getSubtraction(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("Первый аргумент null, матрица не может быть null");
        }

        if (matrix2 == null) {
            throw new NullPointerException("Второй аргумент null, матрица не может быть null");
        }

        if (matrix1.getRowsCount() != matrix2.getRowsCount() && matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Размерность матриц не совпадает");
        }

        Matrix matrix = new Matrix(matrix1);

        return matrix.subtract(matrix2);
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("Первый аргумент null, матрица не может быть null");
        }

        if (matrix2 == null) {
            throw new NullPointerException("Второй аргумент null, матрица не может быть null");
        }

        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Размерность матриц не совпадает");
        }

        Matrix tempMatrix = new Matrix(matrix1.getColumnsCount(), matrix1.getRowsCount());

        for (int row = 0; row < matrix1.getRowsCount(); row++) {
            for (int column = 0; column < matrix2.getColumnsCount(); column++) {
                tempMatrix.rows[row].setComponent(column, Vector.getVectorsProduct(matrix1.rows[row], matrix2.getColumn(column)));
            }
        }

        return tempMatrix;
    }
}