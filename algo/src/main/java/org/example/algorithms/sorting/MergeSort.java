package org.example.algorithms.sorting;

import org.example.metrics.Metrics;

public class MergeSort {

    private static final int INSERTION_SORT_CUTOFF = 15;

    public Metrics sort(int[] array) {
        Metrics metrics = new Metrics();
        if (array == null || array.length <= 1) {
            return metrics;
        }

        int[] buffer = new int[array.length];

        long startTime = System.nanoTime();
        mergeSortRecursive(array, buffer, 0, array.length - 1, 1, metrics);
        long endTime = System.nanoTime();

        metrics.setExecutionTime(endTime - startTime);
        return metrics;
    }

    private void mergeSortRecursive(int[] array, int[] buffer, int left, int right, int depth, Metrics metrics) {
        metrics.recordDepth(depth);

        if (right - left <= INSERTION_SORT_CUTOFF) {
            InsertionSort.sort(array, left, right, metrics);
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSortRecursive(array, buffer, left, mid, depth + 1, metrics);
        mergeSortRecursive(array, buffer, mid + 1, right, depth + 1, metrics);

        merge(array, buffer, left, mid, right, metrics);
    }

    private void merge(int[] array, int[] buffer, int left, int mid, int right, Metrics metrics) {
        System.arraycopy(array, left, buffer, left, right - left + 1);

        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i > mid) {
                array[k] = buffer[j++];
            } else if (j > right) {
                array[k] = buffer[i++];
            } else {
                metrics.incrementComparisons();
                if (buffer[i] <= buffer[j]) {
                    array[k] = buffer[i++];
                } else {
                    array[k] = buffer[j++];
                }
            }
        }
    }
}