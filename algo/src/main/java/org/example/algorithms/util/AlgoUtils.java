package org.example.algorithms.util;

import org.example.metrics.Metrics;

import java.util.Random;

public final class AlgoUtils {

    private AlgoUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void swap(int[] array, int i, int j, Metrics metrics) {
        if (i == j) return;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        if (metrics != null) {
            metrics.incrementSwaps();
        }
    }

    public static int partition(int[] array, int left, int right, Random random, Metrics metrics) {
        int pivotIndex = left + random.nextInt(right - left + 1);
        int pivotValue = array[pivotIndex];
        swap(array, pivotIndex, right, metrics);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (metrics != null) {
                metrics.incrementComparisons();
            }
            if (array[i] < pivotValue) {
                swap(array, i, storeIndex, metrics);
                storeIndex++;
            }
        }

        swap(array, storeIndex, right, metrics);
        return storeIndex;
    }

    public static void shuffle(int[] array, Random random) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(array, i, j, null);
        }
    }
}