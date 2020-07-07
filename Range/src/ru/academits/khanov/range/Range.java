package ru.academits.khanov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        if (from <= to) {
            this.from = from;
            this.to = to;
        } else {
            this.from = to;
            this.to = from;
        }
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
        return from <= number & to >= number;
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }

    public Range getIntersection(Range range) {
        if (Math.min(to, range.to) - Math.max(from, range.from) <= 0) {
            return null;
        }

        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    public Range[] getUnion(Range range) {
        if (Math.min(to, range.to) - Math.max(from, range.from) < 0) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        if (Math.min(to, range.to) - Math.max(from, range.from) <= 0) {
            return new Range[]{new Range(from, to)};
        }

        if (range.to >= to && range.from <= from) {
            return null;
        }

        if (range.from < from) {
            return new Range[]{new Range(range.to, to)};
        }

        if (range.to > to) {
            return new Range[]{new Range(from, range.from)};
        }

        if (range.from == from) {
            return new Range[]{new Range(to, range.to)};
        }

        if (range.to == to) {
            return new Range[]{new Range(range.from, from)};
        }

        return new Range[]{new Range(range.from, from), new Range(to, range.to)};
    }
}
