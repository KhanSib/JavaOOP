package ru.academits.khanov.shape;

public class Square implements Shape {
    private final double length;

    public Square(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Квадрат {" + length + "}";
    }

    @Override
    public double getWidth() {
        return length;
    }

    @Override
    public double getHeight() {
        return length;
    }

    @Override
    public double getArea() {
        return length * length;
    }

    @Override
    public double getPerimeter() {
        return 4 * length;
    }

    @Override
    public int hashCode() {
        final int prime = 41;
        int hash = 1;
        hash = prime * hash + Double.hashCode(length);
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

        Square square = (Square) obj;

        return length == square.length;
    }
}