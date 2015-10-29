package dev.codebase.gcj.sandbox;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SortTests {

    @Test
    public void nullArray() {
        assertNull(SortSelection.bubbleSort(null));
        assertNull(SortSelection.insertionSort(null));        
        assertNull(SortSelection.mergeSort(null));
        assertNull(SortSelection.quickSort(null));        
    }

    @Test
    public void emptyArray() {
        int[] array = new int[10];
        int[] expectedResult = Arrays.copyOf(array, array.length);
        
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, SortSelection.bubbleSort(array));
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, SortSelection.insertionSort(array));
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, SortSelection.mergeSort(array));
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, SortSelection.quickSort(array));        
    }

    @Test
    public void oneElementArray() {
        int[] array = new int[] { 7 };
        int[] expectedResult = Arrays.copyOf(array, array.length);
        
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, SortSelection.bubbleSort(array));
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, SortSelection.insertionSort(array));
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, SortSelection.mergeSort(array));
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, SortSelection.quickSort(array));        
    }

    @Test
    public void sparsePositiveArray() {
        int[] array = new int[5];
        array[2] = 3;
        int[] expectedResult = {0, 0, 0, 0, 3};
        
        int[] sorted = SortSelection.bubbleSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
        
        array = new int[5];
        array[2] = 3;
        sorted = SortSelection.insertionSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
        
        array = new int[5];
        array[2] = 3;
        sorted = SortSelection.mergeSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
        
        array = new int[5];
        array[2] = 3;
        sorted = SortSelection.quickSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
    }
    
    @Test
    public void sparseNegativeArray() {
        int[] array = new int[5];
        array[2] = -3;
        int[] expectedResult = {-3, 0, 0, 0, 0};
        
        int[] sorted = SortSelection.bubbleSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
        
        array = new int[5];
        array[2] = -3;
        sorted = SortSelection.insertionSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
        
        array = new int[5];
        array[2] = -3;
        sorted = SortSelection.mergeSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
        
        array = new int[5];
        array[2] = -3;
        sorted = SortSelection.quickSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
    }
    
    @Test
    public void duplicateElementsArray() {
        int[] array = new int[] {5, 6, 5, 6, 5, 1, 8, 8, 1};
        int[] expectedResult = {1, 1, 5, 5, 5, 6, 6, 8, 8};
        
        int[] sorted = SortSelection.bubbleSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
        
        array = new int[] {5, 6, 5, 6, 5, 1, 8, 8, 1};
        sorted = SortSelection.insertionSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
        
        array = new int[] {5, 6, 5, 6, 5, 1, 8, 8, 1};
        sorted = SortSelection.mergeSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
        
        array = new int[] {5, 6, 5, 6, 5, 1, 8, 8, 1};
        sorted = SortSelection.quickSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
    }

    @Test
    public void mixedSignArray() {
        int[] array = new int[] {5, -6, 4, 6, -5, -1, -8, 2, 8, 1};
        int[] expectedResult = {-8, -6, -5, -1, 1, 2, 4, 5, 6, 8};
        
        int[] sorted = SortSelection.bubbleSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
        
        array = new int[] {5, -6, 4, 6, -5, -1, -8, 2, 8, 1};
        sorted = SortSelection.insertionSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
        
        array = new int[] {5, -6, 4, 6, -5, -1, -8, 2, 8, 1};
        sorted = SortSelection.mergeSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
        
        array = new int[] {5, -6, 4, 6, -5, -1, -8, 2, 8, 1};
        sorted = SortSelection.quickSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
    }

    @Test
    public void sortedArray() {
        int[] array = new int[5];
        array[2] = -3;
        int[] expectedResult = {-3, 0, 0, 0, 0};
        
        int[] sorted = SortSelection.bubbleSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
        
        array = new int[5];
        array[2] = -3;
        sorted = SortSelection.insertionSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
        
        array = new int[5];
        array[2] = -3;
        sorted = SortSelection.mergeSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
        
        array = new int[5];
        array[2] = -3;
        sorted = SortSelection.quickSort(array);
        assertArrayEquals("Expected : " + arrayToStr(expectedResult), expectedResult, sorted);
    }

    public static String arrayToStr(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i] + (i == array.length - 1 ? "]" : ", "));
        }
        
        return sb.toString();
    }
    
}