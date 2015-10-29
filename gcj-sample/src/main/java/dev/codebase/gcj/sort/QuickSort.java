package dev.codebase.gcj.sort;

import java.util.ArrayList;
import java.util.List;

/*
 * Quicksort or partition-exchange sort, is a fast sorting algorithm, which is
 * using divide and conquer algorithm. Quicksort first divides a large list into
 * two smaller sub-lists: the low elements and the high elements. Quicksort can
 * then recursively sort the sub-lists.
 * 
 * Steps to implement Quick sort:
 * 
 * 1) Choose an element, called pivot, from the list. Generally pivot can be the
 *    middle index element.
 *     
 * 2) Reorder the list so that all elements with values less than the pivot come 
 *    before the pivot, while all elements with values greater than the pivot come 
 *    after it (equal values can go either way). After this partitioning, the pivot 
 *    is in its final position. This is called the partition operation.
 *     
 * 3) Recursively apply the above steps to the sub-list of elements with smaller 
 *    values and separately the sub-list of elements with greater values.
 *
 * The complexity of quick sort in the average case is Θ(n log(n)) and in the worst 
 * case is Θ(n2).
 */
public class QuickSort {

    private static int swapCount;
    
    public static void doQuickSort(int[] input) {

        if (input == null || input.length == 0) {
            return;
        }
        
        swapCount = 0;
        
        quickSort(input, 0, input.length - 1);
    }

    private static void quickSort(int[] array, int lowerIndex, int higherIndex) {

        int i = lowerIndex;
        int j = higherIndex;

        // calculate pivot number, I am taking pivot as middle index number
        int pivotIndex = lowerIndex + (higherIndex - lowerIndex) / 2;
        int pivot = array[pivotIndex];
        
        System.out.format("Sort : lower = [%2d] / higher = [%2d] / pivot = [%2d]%n",
                           lowerIndex, higherIndex, pivotIndex);
        System.out.format("               |%2d|            |%2d|           |%2d|%n%n",
                           array[lowerIndex], array[higherIndex], pivot);
        
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a
             * number from right side which is less then the pivot value. Once
             * the search is done, then we exchange both numbers.
             */
            while (array[i] < pivot) {
                i++;
            }
            
            while (array[j] > pivot) {
                j--;
            }
            
            if (i <= j) {
                swapNumbers(i, j, array);
                
                // move index to next position on both sides
                i++;
                j--;
            }
        }
        
        // call quickSort() method recursively
        if (i < higherIndex) {
            System.out.format("i (%2d) < higherIndex (%2d)%n", i, higherIndex);
            quickSort(array, i, higherIndex);
        }
        
        if (lowerIndex < j) {
            System.out.format("lowerIndex (%2d) < j (%2d)%n", lowerIndex, j);
            quickSort(array, lowerIndex, j);
        }
        
    }

    private static void swapNumbers(int i, int j, int[] array) {

        System.out.format("\tSwap(%2d) : [%2d] = %2d <--> [%2d] = %2d%n", 
                          ++swapCount, i, array[i], j, array[j]);

        StringBuffer sbIndex = new StringBuffer("\t");
        StringBuffer separator = new StringBuffer("\t");
        StringBuffer sb = new StringBuffer("\t ");
        StringBuffer sepLR = new StringBuffer("\t");
        StringBuffer sepRL = new StringBuffer("\t");
        
        for (int k = 0; k < array.length; k++) {
            separator.append(k == i ? "|ii|" : (k == j ? "|jj|" : "----"));
            sbIndex.append(String.format("[%-2d]", k));
            sb.append(String.format("%2d", array[k]) + (k == array.length - 1 ? "" : ", "));
            sepLR.append(k == i ? "_vv_" : (k == j ? "_^^_" : "____"));                
            sepRL.append(k == i ? " ^^ " : (k == j ? " vv " : "    "));                
        }
                    
        System.out.println(separator);            
        System.out.println(sbIndex.toString());
        System.out.println(sb.toString());
        System.out.println(sepLR);
        System.out.println(sepRL);
        
        // Swapping ints without a temp variable
        // Only works if values are different...besides, no need to swap if same
        if (i != j) {
            array[i] = array[i] ^ array[j];
            array[j] = array[i] ^ array[j];
            array[i] = array[i] ^ array[j];
        }

        sb = new StringBuffer("\t ");
        
        for (int k = 0; k < array.length; k++) {
            sb.append(String.format("%2d", array[k]) + (k == array.length - 1 ? "" : ", "));
        }
        
        System.out.println(sb.toString());            
        System.out.println(sbIndex.toString() + "\n");                            
    }

    
    /*
     * Another implementation from Java Programming Interviews Exposed
     * 
     * This one sorts a List rather than an array
     * It returns a new List.
     */
    public static List<Integer> quicksort(List<Integer> numbers) {
        
        // List containing less than 2 members guaranteed to already be sorted
        if (numbers.size() < 2) {
            return numbers;
        }
        
        // Using the first member as the pivot
        final Integer pivot = numbers.get(0);
        final List<Integer> lower = new ArrayList<>();
        final List<Integer> higher = new ArrayList<>();
        
        // Start from the 2nd element as 1st element is pivot
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) < pivot) {
                lower.add(numbers.get(i));
            } else {
                higher.add(numbers.get(i));
            }
        }
        
        // Build the output list from:
        //  - the sorted lower list
        //  - the pivot
        //  - the sorted higher list
        final List<Integer> sorted = quicksort(lower);
        sorted.add(pivot);
        sorted.addAll(quicksort(higher));
        
        return sorted;
    }
    
    public static void main(String a[]) {

        int[] input = { 24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12 };
        
        doQuickSort(input);
    }
}
