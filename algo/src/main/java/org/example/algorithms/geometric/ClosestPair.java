package org.example.algorithms.geometric;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClosestPair {

    public PointPair findClosestPair(Point[] points) {
        if (points == null || points.length < 2) {
            return null;
        }

        Point[] pointsSortedByX = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsSortedByX, Comparator.comparingDouble(p -> p.x));

        return findClosestPairRecursive(pointsSortedByX, 0, pointsSortedByX.length - 1);
    }

    private PointPair findClosestPairRecursive(Point[] pointsSortedByX, int left, int right) {
        if (right - left < 3) {
            return bruteForce(pointsSortedByX, left, right);
        }

        int mid = left + (right - left) / 2;
        Point midPoint = pointsSortedByX[mid];

        PointPair leftPair = findClosestPairRecursive(pointsSortedByX, left, mid);
        PointPair rightPair = findClosestPairRecursive(pointsSortedByX, mid + 1, right);

        PointPair minPair;
        if (leftPair.distance < rightPair.distance) {
            minPair = leftPair;
        } else {
            minPair = rightPair;
        }

        List<Point> strip = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(pointsSortedByX[i].x - midPoint.x) < minPair.distance) {
                strip.add(pointsSortedByX[i]);
            }
        }

        strip.sort(Comparator.comparingDouble(p -> p.y));

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < minPair.distance; j++) {
                double dist = strip.get(i).distanceTo(strip.get(j));
                if (dist < minPair.distance) {
                    minPair = new PointPair(strip.get(i), strip.get(j));
                }
            }
        }

        return minPair;
    }

    private PointPair bruteForce(Point[] points, int left, int right) {
        PointPair minPair = null;
        double minDistance = Double.POSITIVE_INFINITY;

        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double dist = points[i].distanceTo(points[j]);
                if (dist < minDistance) {
                    minDistance = dist;
                    minPair = new PointPair(points[i], points[j]);
                }
            }
        }
        return minPair;
    }
}