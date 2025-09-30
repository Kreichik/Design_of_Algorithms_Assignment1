package org.example.algorithms.selection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeterministicSelectTest {

    private DeterministicSelect deterministicSelect;
    private final Random random = new Random();

    @BeforeEach
    void setUp() {
        deterministicSelect = new DeterministicSelect();
    }

    @Test
    void testSelectOnSmallArray() {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6};
        assertEquals(1, deterministicSelect.select(array.clone(), 1));
        assertEquals(1, deterministicSelect.select(array.clone(), 2));
        assertEquals(4, deterministicSelect.select(array.clone(), 5));
        assertEquals(9, deterministicSelect.select(array.clone(), 8));
    }

    @Test
    void testSelectMedian() {
        int[] array = {9, 2, 5, 1, 8, 3, 7, 4, 6};
        assertEquals(5, deterministicSelect.select(array.clone(), 5));
    }

    @Test
    void testSelectWithDuplicates() {
        int[] array = {5, 2, 8, 2, 5, 9, 1, 5};
        assertEquals(5, deterministicSelect.select(array.clone(), 4));
        assertEquals(5, deterministicSelect.select(array.clone(), 5));
        assertEquals(5, deterministicSelect.select(array.clone(), 6));
    }


    @Test
    void testInvalidK() {
        int[] array = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> deterministicSelect.select(array, 0));
        assertThrows(IllegalArgumentException.class, () -> deterministicSelect.select(array, 4));
    }
}