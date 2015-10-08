package dev.codebase.gcj.sort;

/*
 * A simple sorting algorithm that works by repeatedly stepping through the list to be sorted, 
 * comparing each pair of adjacent items and swapping them if they are in the wrong order. 
 * The pass through the list is repeated until no swaps are needed, which indicates that the 
 * list is sorted.
 * 
 * The algorithm gets its name from the way smaller elements "bubble" to the top of the list. 
 * Because it only uses comparisons to operate on elements, it is a comparison sort. Although 
 * the algorithm is simple, most of the other sorting algorithms are more efficient for large 
 * lists.
 * 
 * Bubble sort has worst-case and average complexity both О(n2), where n is the number of items 
 * being sorted. There exist many sorting algorithms with substantially better worst-case or 
 * average complexity of O(n log n). Even other О(n2) sorting algorithms, such as insertion sort, 
 * tend to have better performance than bubble sort. Therefore, bubble sort is not a practical 
 * sorting algorithm when n is large.Performance of bubble sort over an already-sorted list 
 * (best-case) is O(n).
 */
public class BubbleSort {

    private static int sortCount;
    private static int swapCount;
    private static int comparisonCount;
    
    // logic to sort the elements
    public static void doBubbleSort(int array[]) {
        
        sortCount = 0;
        swapCount = 0;
        comparisonCount = 0;
        
        // Decrease the range of the array processed each time by 1
        // largest value in range will be moved to end of range each time 
        for (int m = array.length - 1; m >= 0; m--) {
            
            for (int i = 0; i < m; i++) {
                comparisonCount++;
                
                if (array[i] > array[i + 1]) {
                    swapNumbers(i, i + 1, array);
                    swapCount++;
                }
            }
            
            printNumbers(array);
            
            // If no numbers swapped we know we've finished
            if (swapCount == 0) {
                break;
            } else {
                swapCount = 0;
            }
        }
    }

    private static void swapNumbers(int i, int j, int[] array) {

        // Swapping ints without a temp variable
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
        
    }

    private static void printNumbers(int[] input) {

        StringBuilder sb = new StringBuilder(String.format("[%-2d / %02d / %d] : ", 
                                                           ++sortCount, 
                                                           comparisonCount, 
                                                           swapCount));
        
        for (int i = 0; i < input.length; i++) {
            sb.append(input[i] + (i == input.length - 1 ? "" : ", "));
        }
        
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        
        int[] input = { 4, 2, 9, 6, 23, 12, 34, 0, 1, 21 };
        doBubbleSort(input);
        
        System.out.println();
        
        int[] input2 = { 65, 27, -4, 12, 76, 0, -8, 70, 3, 7 };
        doBubbleSort(input2);
        
        System.out.println();
        
        int[] input3 = { 50, 40, 30, 20, 10, 0, -2, -5, -10, -20 };
        doBubbleSort(input3);
    }
    
}
