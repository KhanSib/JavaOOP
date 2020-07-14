package ru.academits.khanov.shape.shapes;

import ru.academits.khanov.shape.Shape;

public class Triangle implements Shape {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public String toString() {
        return "Треугольник {" + x1 + ", " + y1 + ", " + x2 + ", " + y2 + ", " + x3 + ", " + y3 + "}";
    }

    private static double getMax(double a, double b, double c) {
        if (a >= b && a >= c) {
            return a;
        }

        return Math.max(b, c);
    }

    private static double getMin(double a, double b, double c) {
        if (a <= b && a <= c) {
            return a;
        }

        return Math.min(b, c);
    }

    private static double getMiddle(double a, double b, double c) {
        if (a <= b && b <= c) {
            return b;
        }

        if (b <= a && a <= c) {
            return a;
        }

        return c;
    }

    private static double getLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public double getWidth() {
        return getMax(x1, x2, x3) - getMin(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return getMax(y1, y2, y3) - getMin(y1, y2, y3);
    }

    @Override
    public double getArea() {
        double perimeter = getPerimeter();
        return Math.sqrt(perimeter / 2 * (perimeter / 2 - getLength(x1, y1, x2, y2))
                * (perimeter / 2 - getLength(x1, y1, x3, y3)) * (perimeter / 2 - getLength(x2, y2, x3, y3)));
    }

    @Override
    public double getPerimeter() {
        return getLength(x1, y1, x2, y2) + getLength(x1, y1, x3, y3) + getLength(x2, y2, x3, y3);
    }

    @Override
    public int hashCode() {
        final int prime = 41;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) obj;

        return x1 == triangle.x1 && y1 == triangle.y1 && x2 == triangle.x2 && y2 == triangle.y2 &&
                x3 == triangle.x3 && y3 == triangle.y3;
    }
}