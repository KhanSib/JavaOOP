package ru.academits.khanov.range.main;

import ru.academits.khanov.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range a = new Range(3, 5);
        Range b = new Range(6, 7);
        Range c = new Range(1, 7);

        System.out.println("Отрезок a: " + a);
        System.out.println("Отрезок b: " + b);
        System.out.println("Отрезок c: " + c);

        System.out.println("Входит ли в a левый конец c: " + a.isInside(c.getFrom()));

        System.out.println("Длина отрезка a = " + a.getLength());

        System.out.print("Пересечение отрезков a и b: ");

        if (a.getIntersection(b) == null) {
            System.out.println("Пересечения нет");
        } else {
            System.out.println(a.getIntersection(b));
        }

        System.out.print("Пересечение отрезков c и a: ");

        if (c.getIntersection(a) == null) {
            System.out.println("Пересечения нет");
        } else {
            System.out.println(c.getIntersection(a));
        }

        System.out.print("Объединение отрезков a и c: ");
        System.out.println(Arrays.toString(a.getUnion(c)));

        System.out.print("Разность отрезков a \\ b: ");
        System.out.println(Arrays.toString(a.getDifference(b)));

        System.out.print("Разность отрезков b \\ c: ");
        System.out.println(Arrays.toString(b.getDifference(c)));

        System.out.print("Разность отрезков c \\ a: ");
        System.out.println(Arrays.toString(c.getDifference(a)));
    }
}