package org.example.algorithms.sorting;

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
            int pivotIndex = partition(array, left, right, metrics);

            if (pivotIndex - left < right - pivotIndex) {
                quickSortLoop(array, left, pivotIndex - 1, depth + 1, metrics);
                left = pivotIndex + 1;
            } else {
                quickSortLoop(array, pivotIndex + 1, right, depth + 1, metrics);
                right = pivotIndex - 1;
            }
        }
    }

    private int partition(int[] array, int left, int right, Metrics metrics) {
        int pivotIndex = left + random.nextInt(right - left + 1);
        int pivotValue = array[pivotIndex];
        swap(array, pivotIndex, right, metrics);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            metrics.incrementComparisons();
            if (array[i] < pivotValue) {
                swap(array, i, storeIndex, metrics);
                storeIndex++;
            }
        }

        swap(array, storeIndex, right, metrics);
        return storeIndex;
    }

    private void swap(int[] array, int i, int j, Metrics metrics) {
        if (i == j) return;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        metrics.incrementSwaps();
    }
}