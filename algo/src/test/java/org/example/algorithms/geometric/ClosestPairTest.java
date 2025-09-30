package org.example.algorithms.geometric;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClosestPairTest {

    private ClosestPair closestPair;

    @BeforeEach
    void setUp() {
        closestPair = new ClosestPair();
    }

    @Test
    void testEmptyAndSinglePoint() {
        assertNull(closestPair.findClosestPair(new Point[]{}));
        assertNull(closestPair.findClosestPair(new Point[]{new Point(1, 1)}));
    }

    @Test
    void testSimpleCase() {
        Point[] points = {
                new Point(2, 3), new Point(12, 30),
                new Point(40, 50), new Point(5, 1),
                new Point(12, 10), new Point(3, 4)
        };
        PointPair result = closestPair.findClosestPair(points);
        assertEquals(1.414, result.distance, 0.001);
    }

    @Test
    void testCollinearPoints() {
        Point[] points = {new Point(0, 0), new Point(0, 1), new Point(0, 5), new Point(0, 10)};
        PointPair result = closestPair.findClosestPair(points);
        assertEquals(1.0, result.distance, 0.001);
    }

    @Test
    void testWithBruteForce() {
        for (int i = 0; i < 50; i++) {
            Point[] points = generateRandomPoints(100, 1000);
            PointPair fastResult = closestPair.findClosestPair(points);
            PointPair bruteForceResult = bruteForce(points);
            assertEquals(bruteForceResult.distance, fastResult.distance, 0.00001);
        }
    }

    private Point[] generateRandomPoints(int numPoints, int maxCoord) {
        Random rand = new Random();
        Point[] points = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            points[i] = new Point(rand.nextDouble() * maxCoord, rand.nextDouble() * maxCoord);
        }
        return points;
    }

    private PointPair bruteForce(Point[] points) {
        if (points == null || points.length < 2) return null;
        PointPair minPair = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
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