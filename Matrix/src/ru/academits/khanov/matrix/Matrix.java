package ru.academits.khanov.matrix;

import ru.academits.khanov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] vectors;

    public Matrix(int columnCount, int rowCount) {
        if (columnCount <= 0) {
            throw new IndexOutOfBoundsException("Количество столбцов не может быть нуливым или отрицательным");
        }

        if (rowCount <= 0) {
            throw new IndexOutOfBoundsException("Количество строк не может быть нуливым или отрицательным");
        }

        vectors = new Vector[rowCount];

        for (int i = 0; i < vectors.length; i++) {
            vectors[i] = new Vector(columnCount);
        }
    }

    public Matrix(Matrix matrix) {
        vectors = new Vector[matrix.vectors.length];

        for (int i = 0; i < matrix.vectors.length; i++) {
            vectors[i] = new Vector(matrix.vectors[i]);
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

        vectors = new Vector[array.length];

        int maxLength = 0;

        for (double[] doubles : array) {
            if (maxLength < doubles.length) {
                maxLength = doubles.length;
            }
        }

        for (int i = 0; i < array.length; i++) {
            vectors[i] = new Vector(maxLength, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new NullPointerException("Массив векторов не может быть null");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("Матрица не может быть 0 размерности");
        }

        for (Vector vector : vectors) {
            if (vector == null) {
                throw new NullPointerException("Ни один из векторов не может быть null");
            }
        }

        int maxLength = 0;

        for (Vector elements : vectors) {
            if (maxLength < elements.getSize()) {
                maxLength = elements.getSize();
            }
        }

        this.vectors = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            this.vectors[i] = new Vector(maxLength).add(vectors[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('{');

        for (int i = 0; i < vectors.length - 1; i++) {
            stringBuilder.append(vectors[i]);
            stringBuilder.append(", ");
        }

        stringBuilder.append(vectors[vectors.length - 1]);
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    public int getColumnCount() {
        return vectors.length;
    }

    public int getRowCount() {
        return vectors[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= vectors.length) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера матрицы");
        }

        return new Vector(vectors[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= vectors.length) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера матрицы");
        }

        if (vector == null) {
            throw new IndexOutOfBoundsException("Вектор не может быть null");
        }

        if (vector.getSize() != vectors[0].getSize()) {
            throw new IndexOutOfBoundsException("Длина вектора не соответсвует размеру строк матрицы");
        }

        vectors[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= vectors[0].getSize()) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера матрицы");
        }

        Vector vector = new Vector(vectors.length);

        for (int i = 0; i < vectors.length; i++) {
            vector.setComponent(i, vectors[i].getComponent(index));
        }

        return vector;
    }

    public Matrix transposeMatrix() {
        int columnCount = getColumnCount();
        int rowCount = getRowCount();

        if (columnCount <= rowCount) {
            vectors = Arrays.copyOf(vectors, rowCount);

            for (int i = columnCount; i < rowCount; i++) {
                vectors[i] = new Vector(rowCount);
            }
        } else {
            for (int i = 0; i < columnCount; i++) {
                vectors[i] = new Vector(columnCount, vectors[i]);
            }
        }

        for (int i = 0; i < getColumnCount(); i++) {
            for (int j = 0; j <= i; j++) {
                double tempComponent = vectors[i].getComponent(j);
                vectors[i].setComponent(j, vectors[j].getComponent(i));
                vectors[j].setComponent(i, tempComponent);
            }
        }

        if (columnCount >= rowCount) {
            vectors = Arrays.copyOf(vectors, rowCount);
        } else {
            for (int i = 0; i < rowCount; i++) {
                vectors[i] = new Vector(columnCount, vectors[i]);
            }
        }

        return this;
    }

    public Matrix multiplyByScalar(double scalar) {
        for (Vector vector : vectors) {
            vector.multiplyByScalar(scalar);
        }

        return this;
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не может быть null");
        }

        if (vector.getSize() != vectors[0].getSize()) {
            throw new IndexOutOfBoundsException("Длина вектора не соответсвует длине строки матрицы");
        }

        Vector outVector = new Vector(vectors.length);

        for (int i = 0; i < vectors.length; i++) {
            outVector.setComponent(i, Vector.getVectorsProduct(vector, vectors[i]));
        }

        return outVector;
    }

    public Matrix add(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Аргумент не может быть null");
        }

        if (vectors.length != matrix.vectors.length) {
            throw new IndexOutOfBoundsException("Кол-во строк матрицы аргумента не равно кол-ву строк матрицы");
        }

        if (vectors[0].getSize() != matrix.vectors[0].getSize()) {
            throw new IndexOutOfBoundsException("Кол-во столбцов матрицы аргумента не равно кол-ву столбцов матрицы");
        }

        for (int i = 0; i < matrix.vectors.length; i++) {
            vectors[i].add(matrix.vectors[i]);
        }

        return this;
    }

    public Matrix subtract(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Аргумент не может быть null");
        }

        if (vectors.length != matrix.vectors.length) {
            throw new IndexOutOfBoundsException("Кол-во строк матрицы аргумента не равно кол-ву строк матрицы");
        }

        if (vectors[0].getSize() != matrix.vectors[0].getSize()) {
            throw new IndexOutOfBoundsException("Кол-во столбцов матрицы аргумента не равно кол-ву столбцов матрицы");
        }

        for (int i = 0; i < matrix.vectors.length; i++) {
            vectors[i].subtract(matrix.vectors[i]);
        }

        return this;
    }

    public double getDeterminant() {
        if (vectors.length != vectors[0].getSize()) {
            throw new IndexOutOfBoundsException("Матрица должна быть квадратной");
        }

        int matrixSize = vectors.length;
        double epsilon = 1.0e-10;
        double[][] triangularMatrix = new double[matrixSize][matrixSize];

        for (int row = 0; row < matrixSize; row++) {
            for (int column = 0; column < matrixSize; column++) {
                triangularMatrix[row][column] = vectors[row].getComponent(column);
            }
        }

        for (int i = 0; i < matrixSize - 1; i++) {
            for (int j = i + 1; j < matrixSize; j++) {
                if (Math.abs(triangularMatrix[i][i]) < epsilon) {
                    continue;
                }

                double reductionCoefficient = triangularMatrix[i][j] / triangularMatrix[i][i];

                for (int k = i; k < matrixSize; k++) {
                    triangularMatrix[k][j] -= reductionCoefficient * triangularMatrix[k][i];
                }
            }
        }

        double determinant = 1;

        for (int i = 0; i < matrixSize; i++) {
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

        if (matrix1.getRowCount() == 0 || matrix1.getColumnCount() == 0) {
            throw new IndexOutOfBoundsException("Первый аргумент, матрица не может быть нулевой размерности");
        }

        if (matrix2.getRowCount() == 0 || matrix2.getColumnCount() == 0) {
            throw new IndexOutOfBoundsException("Второй аргумент, матрица не может быть нулевой размерности");
        }

        if (matrix1.getRowCount() != matrix2.getRowCount() && matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IndexOutOfBoundsException("Размерность матриц не совпадает");
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

        if (matrix1.getRowCount() == 0 || matrix1.getColumnCount() == 0) {
            throw new IndexOutOfBoundsException("Первый аргумент, матрица не может быть нулевой размерности");
        }

        if (matrix2.getRowCount() == 0 || matrix2.getColumnCount() == 0) {
            throw new IndexOutOfBoundsException("Второй аргумент, матрица не может быть нулевой размерности");
        }

        if (matrix1.getRowCount() != matrix2.getRowCount() && matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IndexOutOfBoundsException("Размерность матриц не совпадает");
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

        if (matrix1.getRowCount() == 0 || matrix1.getColumnCount() == 0) {
            throw new IndexOutOfBoundsException("Первый аргумент, матрица не может быть нулевой размерности");
        }

        if (matrix2.getRowCount() == 0 || matrix2.getColumnCount() == 0) {
            throw new IndexOutOfBoundsException("Второй аргумент, матрица не может быть нулевой размерности");
        }

        if (matrix1.getRowCount() != matrix2.getRowCount() && matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IndexOutOfBoundsException("Размерность матриц не совпадает");
        }

        Matrix tempMatrix = new Matrix(matrix1.getColumnCount(), matrix1.getRowCount());

        for (int row = 0; row < matrix1.getRowCount(); row++) {
            for (int column = 0; column < matrix2.getColumnCount(); column++) {
                tempMatrix.vectors[row].setComponent(column, Vector.getVectorsProduct(matrix1.vectors[row], matrix2.getColumn(column)));
            }
        }

        return tempMatrix;
    }
}