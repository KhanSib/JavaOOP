package ru.academits.khanov.matrix.main;

import ru.academits.khanov.matrix.Matrix;

public class Main {
    public static void main(String[] args){
        Matrix matrix1 = new Matrix(10,10);

            System.out.println(matrix1);

        double[][] matrix2 = {{1,2,3},{1,3}};

            Matrix matrix3 = new Matrix(matrix2);

        System.out.println(matrix3);
    }
}
