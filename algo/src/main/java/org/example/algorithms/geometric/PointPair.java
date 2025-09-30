package org.example.algorithms.geometric;

public class PointPair {
    public final Point p1;
    public final Point p2;
    public final double distance;

    public PointPair(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.distance = p1.distanceTo(p2);
    }

    @Override
    public String toString() {
        return "Pair: " + p1 + " and " + p2 + ", distance: " + distance;
    }
}