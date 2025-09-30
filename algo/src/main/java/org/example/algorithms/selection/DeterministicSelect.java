package org.example.algorithms.selection;

import org.example.metrics.Metrics;

public class DeterministicSelect {

    private static final int GROUP_SIZE = 5;

    public int select(int[] array, int k) {
        if (array == null || array.length == 0 || k < 1 || k > array.length) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        return selectRecursive(array, 0, array.length - 1, k, new Metrics());
    }

    private int selectRecursive(int[] array, int left, int right, int k, Metrics metrics) {
        while (true) {
            if (left == right) {
                return array[left];
            }

            int pivotIndex = pivot(array, left, right, metrics);
            pivotIndex = partition(array, left, right, pivotIndex, metrics);

            int rank = pivotIndex - left + 1;

            if (k == rank) {
                return array[pivotIndex];
            } else if (k < rank) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
                k = k - rank;
            }
        }
    }

    private int pivot(int[] array, int left, int right, Metrics metrics) {
        if (right - left < GROUP_SIZE) {
            return findMedian(array, left, right, metrics);
        }

        int numGroups = (right - left + 1) / GROUP_SIZE;
        for (int i = 0; i < numGroups; i++) {
            int groupLeft = left + i * GROUP_SIZE;
            int groupRight = groupLeft + GROUP_SIZE - 1;
            int medianIndex = findMedian(array, groupLeft, groupRight, metrics);
            swap(array, medianIndex, left + i, metrics);
        }

        return selectRecursive(array, left, left + numGroups - 1, (numGroups + 1) / 2, metrics);
    }

    private int findMedian(int[] array, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= left && array[j] > key) {
                if(metrics != null) metrics.incrementComparisons();
                array[j + 1] = array[j];
                if(metrics != null) metrics.incrementSwaps();
                j--;
            }
            array[j + 1] = key;
            if(metrics != null) metrics.incrementSwaps();
        }
        return left + (right - left) / 2;
    }

    private int partition(int[] array, int left, int right, int pivotIndex, Metrics metrics) {
        int pivotValue = array[pivotIndex];
        swap(array, pivotIndex, right, metrics);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if(metrics != null) metrics.incrementComparisons();
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
        if (metrics != null) {
            metrics.incrementSwaps();
        }
    }
}