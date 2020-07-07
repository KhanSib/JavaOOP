package ru.academits.khanov.main;

import ru.academits.khanov.range.Range;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range a = new Range(3, 5);
        Range b = new Range(4, 7);
        Range c = new Range(1, 7);

        System.out.println("Отрезок a: " + a);
        System.out.println("Отрезок b: " + b);
        System.out.println("Отрезок c: " + c);

        System.out.println("Входит ли в a левый конец b: " + a.isInside(b.getFrom()));

        System.out.println("Длина отрезка a = " + a.getLength());

        System.out.print("Пересечение отрезков a и b: ");

        if (a.getIntersection(b) == null) {
            System.out.println("Пересечения нет");
        } else {
            System.out.println(a.getIntersection(b));
        }

        System.out.print("Объединение отрезков a и b: ");
        System.out.println(Arrays.toString(a.getUnion(b)));

        System.out.print("Разность отрезков a \\ b: ");
        System.out.println(Arrays.toString(a.getDifference(b)));

        System.out.print("Разность отрезков b \\ c: ");
        System.out.println(Arrays.toString(b.getDifference(c)));
    }
}