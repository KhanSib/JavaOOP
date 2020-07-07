package ru.academits.khanov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return from <= number && to >= number;
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }

    public Range getIntersection(Range range) {
        double minTo = Math.min(to, range.to);
        double maxFrom = Math.min(to, range.to);

        if (minTo - maxFrom <= 0) {
            return null;
        }

        return new Range(maxFrom, minTo);
    }

    public Range[] getUnion(Range range) {
        if (Math.min(to, range.to) - Math.max(from, range.from) < 0) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        if (Math.min(to, range.to) <= Math.max(from, range.from)) {
            return new Range[]{new Range(from, to)};
        }

        if (range.to >= to && range.from <= from) {
            return new Range[]{};
        }

        if (range.from <= from) {
            return new Range[]{new Range(range.to, to)};
        }

        if (range.to >= to) {
            return new Range[]{new Range(from, range.from)};
        }

        return new Range[]{new Range(range.from, from), new Range(to, range.to)};
    }
}