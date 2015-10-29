package dev.codebase.gcj.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Insertion sort is a simple sorting algorithm, it builds the final sorted
 * array one item at a time. It is much less efficient on large lists than other
 * sort algorithms.
 * 
 * Advantages of Insertion Sort: 1) It is very simple. 2) It is very efficient
 * for small data sets. 3) It is stable; i.e., it does not change the relative
 * order of elements with equal keys. 4) In-place; i.e., only requires a
 * constant amount O(1) of additional memory space.
 * 
 * Insertion sort iterates through the list by consuming one input element at
 * each repetition, and growing a sorted output list. On a repetition, insertion
 * sort removes one element from the input data, finds the location it belongs
 * within the sorted list, and inserts it there. It repeats until no input
 * elements remain.
 * 
 * The best case input is an array that is already sorted. In this case
 * insertion sort has a linear running time (i.e., Θ(n)). During each iteration,
 * the first remaining element of the input is only compared with the right-most
 * element of the sorted subsection of the array. The simplest worst case input
 * is an array sorted in reverse order. The set of all worst case inputs
 * consists of all arrays where each element is the smallest or second-smallest
 * of the elements before it. In these cases every iteration of the inner loop
 * will scan and shift the entire sorted subsection of the array before
 * inserting the next element. This gives insertion sort a quadratic running
 * time (i.e., O(n2)). The average case is also quadratic, which makes insertion
 * sort impractical for sorting large arrays. However, insertion sort is one of
 * the fastest algorithms for sorting very small arrays, even faster than
 * quicksort; indeed, good quicksort implementations use insertion sort for
 * arrays smaller than a certain threshold, also when arising as subproblems;
 * the exact threshold must be determined experimentally and depends on the
 * machine, but is commonly around ten.
 */
public class InsertionSort {

    public static void doInsertionSort(int[] input) {

        int swapCount = 0;
        
        // Outer loop : begin at 2nd element
        //              iterate forwards to end
        for (int i = 1; i < input.length; i++) {
            
            // Inner loop : begin at outer loop index
            //              iterate backwards to start            
            for (int j = i; j > 0; j--) {
                if (input[j] < input[j - 1]) {
                    
                    System.out.format("Swap(%d) : [%d] = %d <--> [%d] = %d%n%n", 
                                      ++swapCount, j, input[j], j - 1, input[j - 1]);

                    StringBuffer sbIndex = new StringBuffer("\t");
                    StringBuffer separator = new StringBuffer("\t");
                    StringBuffer sb = new StringBuffer("\t ");
                    StringBuffer sepLR = new StringBuffer("\t");
                    StringBuffer sepRL = new StringBuffer("\t");
                    
                    for (int k = 0; k < input.length; k++) {
                        separator.append(k == j ? "|ii|" : (k == j - 1 ? "|xx|" : "----"));
                        sbIndex.append(String.format("[%-2d]", k));
                        sb.append(String.format("%2d", input[k]) + (k == input.length - 1 ? "" : ", "));
                        sepLR.append(k == j ? "_vv_" : (k == j - 1 ? "_^^_" : "____"));                
                        sepRL.append(k == j ? " ^^ " : (k == j - 1 ? " vv " : "    "));                
                    }
                                
                    System.out.println(separator);            
                    System.out.println(sbIndex.toString());
                    System.out.println(sb.toString());
                    System.out.println(sepLR);
                    System.out.println(sepRL);
                                        
                    swap(j, j - 1, input);
                    
                    sb = new StringBuffer("\t ");
                    
                    for (int k = 0; k < input.length; k++) {
                        sb.append(String.format("%2d", input[k]) + (k == input.length - 1 ? "" : ", "));
                    }
                    
                    System.out.println(sb.toString());            
                    System.out.println(sbIndex.toString() + "\n");                    
                }
            }
        }
        
        return;
    }
    
    public static void doInsertionSortBare(int[] input) {

        // Outer loop : begin at 2nd element
        //              iterate forwards to end
        for (int i = 1; i < input.length; i++) {
            
            // Inner loop : begin at current outer loop index (i)
            //              iterate backwards to start            
            for (int j = i; j > 0; j--) {
                if (input[j] < input[j - 1]) {
                    swap(j, j - 1, input);
                }
            }
        }
        
        return;
    }
    
    private static void swap(int i, int j, int[] array) {

        // Swapping ints without a temp variable
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];

        return;
    }

    /*
     * Another implementation from Java Programming Interviews Exposed
     * 
     * This one sorts a List rather than an array
     * It returns a new List.
     * It uses a LinkedList so that insertions can be efficiently done
     * at specific point in List
     */
    public static List<Integer> insertSort(final List<Integer> numbers) {
        
        final List<Integer> sortedList = new LinkedList<>();
        
        originalList: for (Integer number : numbers) {
            
            for (int i = 0; i < sortedList.size(); i++) {
                if (number < sortedList.get(i)) {
                    sortedList.add(i, number);
                    continue originalList;
                }
            }
            
            // No smaller numbers in sortedList so add to end
            sortedList.add(sortedList.size(), number);
        }
        
        return sortedList;
    }    
    
    public static void main(String a[]) {
        int[] arr = { 10, 34, 2, 56, 7, 67, 88, 42 };
        
        List<Integer> arrInteger = new ArrayList<Integer>();
        
        // Can't autobox an array of primitive so have to loop and autobox each element
        for (int i = 0; i < arr.length; i++) {
            arrInteger.add(arr[i]);
        }
        
        doInsertionSort(arr);
        
        System.out.println("arrInteger prior to insertSort : " + arrInteger);
        System.out.println("arrInteger after insertSort : " + insertSort(arrInteger));

    }

}
