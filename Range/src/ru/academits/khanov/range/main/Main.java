package ru.academits.khanov.range.main;

import ru.academits.khanov.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range a = new Range(3, 5);
        Range b = new Range(6, 7);
        Range c = new Range(1, 7);
        Range d = new Range(6, 10);

        System.out.println("Отрезок a: " + a);
        System.out.println("Отрезок b: " + b);
        System.out.println("Отрезок c: " + c);
        System.out.println("Отрезок d: " + d);

        System.out.println("Входит ли в a левый конец c: " + a.isInside(c.getFrom()));

        System.out.println("Длина отрезка a = " + a.getLength());

        System.out.print("Пересечение отрезков a и b: ");
        Range intersectionAB = a.getIntersection(b);

        if (intersectionAB == null) {
            System.out.println("Пересечения нет");
        } else {
            System.out.println(intersectionAB);
        }

        System.out.print("Пересечение отрезков c и d: ");
        Range intersectionCD = c.getIntersection(d);

        if (intersectionCD == null) {
            System.out.println("Пересечения нет");
        } else {
            System.out.println(intersectionCD);
        }

        System.out.print("Объединение отрезков a и b: ");
        System.out.println(Arrays.toString(a.getUnion(b)));

        System.out.print("Разность отрезков c \\ d: ");
        System.out.println(Arrays.toString(c.getDifference(d)));

        System.out.print("Разность отрезков b \\ c: ");
        System.out.println(Arrays.toString(b.getDifference(c)));

        System.out.print("Разность отрезков a \\ d: ");
        System.out.println(Arrays.toString(a.getDifference(d)));
    }
}