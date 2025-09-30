package org.example.algorithms.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSortTest {

    private MergeSort mergeSort;

    @BeforeEach
    void setUp() {
        mergeSort = new MergeSort();
    }

    @Test
    void testEmptyArray() {
        int[] array = {};
        int[] expected = {};
        mergeSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testSingleElementArray() {
        int[] array = {5};
        int[] expected = {5};
        mergeSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testAlreadySortedArray() {
        int[] array = {1, 2, 3, 4, 5, 6};
        int[] expected = {1, 2, 3, 4, 5, 6};
        mergeSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testReverseSortedArray() {
        int[] array = {6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6};
        mergeSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testArrayWithDuplicates() {
        int[] array = {5, 2, 8, 2, 5, 9, 1};
        int[] expected = {1, 2, 2, 5, 5, 8, 9};
        mergeSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testLargeRandomArray() {
        int[] array = new Random().ints(1000, -1000, 1000).toArray();
        int[] expected = Arrays.copyOf(array, array.length);
        Arrays.sort(expected);
        mergeSort.sort(array);
        assertArrayEquals(expected, array);
    }
}