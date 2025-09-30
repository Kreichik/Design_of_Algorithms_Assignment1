package org.example.algorithms.sorting;

import org.example.algorithms.util.AlgoUtils;
import org.example.metrics.Metrics;

import java.util.Random;

public class QuickSort {

    private final Random random = new Random();

    public Metrics sort(int[] array) {
        Metrics metrics = new Metrics();
        if (array == null || array.length <= 1) {
            return metrics;
        }

        long startTime = System.nanoTime();
        quickSortLoop(array, 0, array.length - 1, 1, metrics);
        long endTime = System.nanoTime();

        metrics.setExecutionTime(endTime - startTime);
        return metrics;
    }

    private void quickSortLoop(int[] array, int left, int right, int depth, Metrics metrics) {
        while (left < right) {
            metrics.recordDepth(depth);
            int pivotIndex = AlgoUtils.partition(array, left, right, random, metrics);

            if (pivotIndex - left < right - pivotIndex) {
                quickSortLoop(array, left, pivotIndex - 1, depth + 1, metrics);
                left = pivotIndex + 1;
            } else {
                quickSortLoop(array, pivotIndex + 1, right, depth + 1, metrics);
                right = pivotIndex - 1;
            }
        }
    }
}