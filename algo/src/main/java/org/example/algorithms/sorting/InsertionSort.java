package org.example.algorithms.sorting;

import org.example.metrics.Metrics;

public class InsertionSort {

    public static void sort(int[] array, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int current = array[i];
            int j = i - 1;

            while (j >= left) {
                metrics.incrementComparisons();
                if (array[j] > current) {
                    array[j + 1] = array[j];
                    metrics.incrementSwaps();
                    j--;
                } else {
                    break;
                }
            }
            array[j + 1] = current;
            metrics.incrementSwaps();
        }
    }
}