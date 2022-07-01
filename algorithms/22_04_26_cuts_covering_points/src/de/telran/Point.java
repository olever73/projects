package de.telran;

 public class Point implements Comparable<Point> {
    int value;
    PointType type;

    public Point(int value, PointType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public int compareTo(Point anotherPoint) {
        int res = this.value - anotherPoint.value;
        if (res != 0)
            return res;

        res = this.type.ordinal() - anotherPoint.type.ordinal();
        return res;
    }
}

