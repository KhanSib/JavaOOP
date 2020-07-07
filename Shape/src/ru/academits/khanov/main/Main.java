package ru.academits.khanov.main;

import ru.academits.khanov.shape.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[]{
                new Square(5),
                new Square(2),
                new Triangle(1, 5, 2, 1, -1, 1),
                new Triangle(1, 1, 3, 3, 5, 5),
                new Rectangle(4, 6),
                new Rectangle(3, 5),
                new Circle(3),
                new Circle(4)};

        Shape shapeWithMaxArea = getShapeWithMaxArea(shapes);
        System.out.println("Фигура с максимальной площадью: " + shapeWithMaxArea);

        Shape shapeWithSecondMaxPerimeter = getShapeWithSecondMaxPerimeter(shapes);
        System.out.println("Фигура со вторым по величине периметром: " + shapeWithSecondMaxPerimeter);
    }

    public static Shape getShapeWithMaxArea(Shape[] shapes) {
        Arrays.sort(shapes, new AreaComparator());
        return shapes[shapes.length - 1];
    }

    public static Shape getShapeWithSecondMaxPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, new PerimeterComparator());
        return shapes[shapes.length - 2];
    }
}