package ru.academits.khanov.matrix;

import ru.academits.khanov.vector.Vector;


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
        int maxLength = 0;

        for (Vector elements : vectors) {
            if (maxLength < elements.getSize()) {
                maxLength = elements.getSize();
            }
        }

        this.vectors = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            this.vectors[i] = new Vector(maxLength);

            for (int j = 0; j < vectors[i].getSize(); j++) {
                this.vectors[i].setComponent(j, vectors[i].getComponent(j));
            }
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

    public int[] getSize() {
        return new int[]{vectors.length, vectors[0].getSize()};
    }

    public Vector getLineVector(int index) {
        if (index < 0 || index >= vectors.length) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера матрицы");
        }
        return vectors[index];
    }

    public void setLineVector(int index, Vector vector) {
        if (index < 0 || index >= vectors.length) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера матрицы");
        }

        if (vector == null) {
            throw new IndexOutOfBoundsException("Вектор не может быть null");
        }

        if (vector.getSize() > vectors[0].getSize()) {
            throw new IndexOutOfBoundsException("Длина вектора выходит за границы размерности матрицы");
        }

        for (int i = 0; i < vectors[index].getSize(); i++) {
            if (i < vector.getSize()) {
                vectors[index].setComponent(i, vector.getComponent(i));
            } else {
                vectors[index].setComponent(i, 0);
            }
        }
    }

    public Vector getColumnVector(int index) {
        if (index < 0 || index >= vectors.length) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы размера матрицы");
        }

        Vector vector = new Vector(vectors.length);

        for (int i = 0; i < vectors.length; i++) {
            vector.setComponent(i, vectors[i].getComponent(index));
        }

        return vector;
    }

    public Matrix getTransposeMatrix() {
        Matrix matrix = new Matrix(vectors.length, vectors[0].getSize());

        for (int column = 0; column < vectors.length; column++) {
            for (int line = 0; line < vectors[0].getSize(); line++) {
                matrix.vectors[line].setComponent(column, vectors[column].getComponent(line));
            }
        }

        return matrix;
    }

    public Matrix increaseByScalar(double scalar) {
        for (Vector vector : vectors) {
            for (int line = 0; line < vectors[0].getSize(); line++) {
                vector.setComponent(line, vector.getComponent(line) * scalar);
            }
        }

        return this;
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new IndexOutOfBoundsException("Вектор не может быть null");
        }

        if (vector.getSize() > vectors[0].getSize()) {
            throw new IndexOutOfBoundsException("Длина вектора выходит за пределы размера строки матрицы");
        }

        Vector outVector = new Vector(vectors[0].getSize());
        Vector inVector = new Vector(vectors[0].getSize());

        for (int i = 0; i < vector.getSize(); i++) {
            inVector.setComponent(i, vector.getComponent(i));
        }


        for (int i = 0; i < vectors.length; i++) {
            outVector.setComponent(i, Vector.getMultiplication(inVector, this.vectors[i]));
        }

        return outVector;
    }

    public Matrix add(Matrix matrix) {
        if (matrix == null) {
            throw new IndexOutOfBoundsException("Аргумент не может быть null");
        }

        if (vectors.length < matrix.vectors.length) {
            throw new IndexOutOfBoundsException("Кол-во строк матрицы аргумента превышает кол-во строк матрицы");
        }

        if (vectors[0].getSize() < matrix.vectors[0].getSize()) {
            throw new IndexOutOfBoundsException("Кол-во столбцов матрицы аргумента превышает кол-во столбцов матрицы");
        }

        for (int i = 0; i < matrix.vectors.length; i++) {
            vectors[i].add(matrix.vectors[i]);
        }

        return this;
    }

    public Matrix subtract(Matrix matrix) {
        if (matrix == null) {
            throw new IndexOutOfBoundsException("Аргумент не может быть null");
        }

        if (vectors.length < matrix.vectors.length) {
            throw new IndexOutOfBoundsException("Кол-во строк матрицы аргумента превышает кол-во строк матрицы");
        }

        if (vectors[0].getSize() < matrix.vectors[0].getSize()) {
            throw new IndexOutOfBoundsException("Кол-во столбцов матрицы аргумента превышает кол-во столбцов матрицы");
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

        for (int line = 0; line < matrixSize; line++) {
            for (int column = 0; column < matrixSize; column++) {
                triangularMatrix[line][column] = vectors[line].getComponent(column);
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

    public static Matrix sum(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("Первый аргумент null, матрица не может быть null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("Второй аргумент null, матрица не может быть null");
        }

        Matrix tempMatrix1 = new Matrix(matrix1);

        return new Matrix(tempMatrix1.add(matrix2));
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("Первый аргумент null, матрица не может быть null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("Второй аргумент null, матрица не может быть null");
        }

        Matrix tempMatrix1 = new Matrix(matrix1);

        return new Matrix(tempMatrix1.subtract(matrix2));
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("Первый аргумент null, матрица не может быть null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("Второй аргумент null, матрица не может быть null");
        }

        Matrix tempMatrix = new Matrix(matrix2.getSize()[0],matrix1.getSize()[1]);

        for (int line=0;line<matrix1.getSize()[1];line++){
            for (int column=0;column<matrix2.getSize()[0];column++) {
                tempMatrix.vectors[line].setComponent(column,Vector.getMultiplication(matrix1.vectors[line],matrix2.getColumnVector(column)));
            }
        }

        return tempMatrix;
    }
}