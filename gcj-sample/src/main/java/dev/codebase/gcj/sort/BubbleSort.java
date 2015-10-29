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

    public static void doBubbleSort(int array[]) {
        
        int totalSwapCount = 0;
        
        // Decrease the range of the array processed each time by 1
        // largest value in range will be moved to end of range each time 
        for (int i = array.length - 1; i >= 0; i--) {
            int swapCount = 0;
            
            for (int j = 0; j < i; j++) {
                
                if (array[j] > array[j + 1]) {
                    
                    System.out.format("Swap(%d) : [%d] = %d <--> [%d] = %d%n%n", 
                                      ++totalSwapCount, j, array[j], j + 1, array[j + 1]);
                    
                    StringBuffer sbIndex = new StringBuffer("\t");
                    StringBuffer separator = new StringBuffer("\t");
                    StringBuffer sb = new StringBuffer("\t ");
                    StringBuffer sepLR = new StringBuffer("\t");
                    StringBuffer sepRL = new StringBuffer("\t");
                      
                    for (int k = 0; k < array.length; k++) {
                        separator.append(k == j ? "|ii|" : (k == j + 1 ? "|xx|" : "----"));
                        sbIndex.append(String.format("[%-2d]", k));
                        sb.append(String.format("%2d", array[k]) + (k == array.length - 1 ? "" : ", "));
                        sepLR.append(k == j ? "_vv_" : (k == j + 1 ? "_^^_" : "____"));                
                        sepRL.append(k == j ? " ^^ " : (k == j + 1 ? " vv " : "    "));                
                    }
                                  
                    System.out.println(separator);            
                    System.out.println(sbIndex.toString());
                    System.out.println(sb.toString());
                    System.out.println(sepLR);
                    System.out.println(sepRL);
                                        
                    swap(j, j + 1, array);
                    swapCount++;
                    
                    sb = new StringBuffer("\t ");
                    
                    for (int k = 0; k < array.length; k++) {
                        sb.append(String.format("%2d", array[k]) + (k == array.length - 1 ? "" : ", "));
                    }
                    
                    System.out.println(sb.toString());            
                    System.out.println(sbIndex.toString() + "\n");                                        
                }
            }
            
            // If no numbers swapped we know we've finished
            if (swapCount == 0) {
                break;
            } else {
                swapCount = 0;
            }
        }
    }

    public static void doBubbleSortBare(int array[]) {
        
        // Outer loop : begin at 2nd element from end
        //              iterate backwards to start
        for (int i = array.length - 1; i >= 0; i--) {
            int swapCount = 0;
            
            // Inner loop : begin at start
            //              iterate forwards to end
            for (int j = 0; j < i; j++) {                
                if (array[j] > array[j + 1]) {                    
                    swap(j, j + 1, array);
                    swapCount++;
                }
            }
            
            // If no numbers swapped we know we've finished
            if (swapCount == 0) {
                break;
            } else {
                swapCount = 0;
            }
        }
    }

    private static void swap(int i, int j, int[] array) {

        // Swapping ints without a temp variable
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
     
        return;
    }

    public static void main(String[] args) {
        
        int[] input = { 65, 27, -4, 12, 76, 0, -8, 70, 3, 7 };
        doBubbleSort(input);
        
        //System.out.println();
        
        //int[] input2 = { 50, 40, 30, 20, 10, 0, -2, -5, -10, -20 };
        //doBubbleSort(input2);
    }
    
}
