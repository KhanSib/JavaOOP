package ru.academits.khanov.vector.main;

import ru.academits.khanov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{10, 20, 30, 40, 50, 60, 70});
        Vector vector2 = new Vector(vector1);

        System.out.println("Вектор 1: " + vector1);
        System.out.println("Вектор 2: " + vector2);

        Vector vector3 = new Vector(6, new double[]{1, 2, 3, 4, 5});
        System.out.println("Вектор 3: " + vector3);

        System.out.println("Вектор 3 + Вектор 1 (not static) " + vector3.add(vector1));
        System.out.println("Вектор 3: " + vector3);

        System.out.println("Вектор 3 - Вектор 1 (not static) " + vector3.subtract(vector1));
        System.out.println("Вектор 3: " + vector3);

        System.out.println("Умножение вектора 3 на скаляр " + vector3.numberMultiplication(10.1111));

        System.out.println("Вектор 3 разворот: " + vector3.turn());

        System.out.println("Вектор 1 длина: " + vector1.getLength());

        System.out.println("Вектор 1 + 2 (static): " + Vector.sum(vector1, vector2));

        System.out.println("Вектор 1 - 2 (static): " + Vector.subtract(vector1, vector2));

        System.out.println("Вектор 1 * 2 (static): " + Vector.getMultiplication(vector1, vector2));

        System.out.println("Вектор 1: " + vector1);
        System.out.println("Вектор 2: " + vector2);

        System.out.println("Равен ли Вектор 1 и Вектор 2: " + vector1.equals(vector2));
    }
}