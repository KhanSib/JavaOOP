package ru.academits.khanov.main;

import ru.academits.khanov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{10, 20, 30, 40, 50});
        Vector vector2 = new Vector(vector1);

        System.out.println("Вектор 1: " + vector1);
        System.out.println("Вектор 2: " + vector2);

        vector2.setVector(new double[]{1, 2, 30});
        System.out.println("Используем set-ер в Vector2 {1, 2, 30}: " + vector2);

        Vector vector3 = new Vector(6, new double[]{1, 2, 3, 4, 5});
        System.out.println("Вектор 3: " + vector3);

        System.out.println("Вектор 3 + Вектор 1 " + vector3.add(vector1));
        System.out.println("Вектор 3: " + vector3);

        System.out.println("Вектор 3 - Вектор 1 " + vector3.subtract(vector1));
        System.out.println("Вектор 3: " + vector3);

        System.out.println("Умножение вектора 3 на скаляр " + vector3.scalarMultiplication(10.1111));

        System.out.println("Вектор 3 разворот: " + vector3.turn());

        System.out.println("Вектор 1 длина: " + vector1.getLength());

        System.out.println("Вектор 1 + 2: " + Vector.sum(vector1, vector2));

        System.out.println("Вектор 1 + 2: " + Vector.subtract(vector1, vector2));

        System.out.println("Вектор 1 + 2: " + Vector.multiplication(vector1, vector2));

        System.out.println("Вектор 1: " + vector1);
        System.out.println("Вектор 2: " + vector2);

        System.out.println("Равен ли Вектор 1 и Вектор 2: " + vector1.equals(vector2));

        vector2.setVector(vector1.getVector());
        System.out.println("Равен ли Вектор 1 и Вектор 2: " + vector1.equals(vector2));
    }
}
