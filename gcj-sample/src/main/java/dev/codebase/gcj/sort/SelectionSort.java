package dev.codebase.gcj.sort;

/*
 * A combination of searching and sorting. During each pass, the unsorted element with the 
 * smallest (or largest) value is moved to its proper position in the array. The number of 
 * times the sort passes through the array is one less than the number of items in the array. 
 * In the selection sort, the inner loop finds the next smallest (or largest) value and the 
 * outer loop places that value into its proper location.
 * 
 * Selection sort is not difficult to analyse compared to other sorting algorithms since none 
 * of the loops depend on the data in the array. Selecting the lowest element requires scanning 
 * all n elements (this takes n − 1 comparisons) and then swapping it into the first position. 
 * Finding the next lowest element requires scanning the remaining n − 1 elements and so on, 
 * for (n − 1) + (n − 2) + ... + 2 + 1 = n(n − 1) / 2 ∈ Θ(n2) comparisons. 
 * 
 * Each of these scans requires one swap for n − 1 elements.
 */
public class SelectionSort {

    public static void doSelectionSort(int[] arr) {

        int swapCount = 0;
        
        // Outer loop : begin at start of array
        //              iterate through to end
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            
            // Inner loop : begin at element one ahead of outer loop index
            //              iterate through to end
            for (int j = i + 1; j < arr.length; j++) {
                // Find the smallest element ahead of outer loop index
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            
            // If outer loop index has no smaller element ahead of it...move on 
            if (i == index) {
                continue;
            }
            
            System.out.format("Swap(%d) : [%d] = %d <--> [%d] = %d%n%n", 
                              ++swapCount, i, arr[i], index, arr[index]);

            StringBuffer sbIndex = new StringBuffer("\t");
            StringBuffer separator = new StringBuffer("\t");
            StringBuffer sb = new StringBuffer("\t ");
            StringBuffer sepLR = new StringBuffer("\t");
            StringBuffer sepRL = new StringBuffer("\t");
            
            for (int k = 0; k < arr.length; k++) {
                separator.append(k == i ? "|ii|" : (k == index ? "|xx|" : "----"));
                sbIndex.append(String.format("[%-2d]", k));
                sb.append(String.format("%2d", arr[k]) + (k == arr.length - 1 ? "" : ", "));
                sepLR.append(k == i ? "_vv_" : (k == index ? "_^^_" : "____"));                
                sepRL.append(k == i ? " ^^ " : (k == index ? " vv " : "    "));                
            }
                        
            System.out.println(separator);            
            System.out.println(sbIndex.toString());
            System.out.println(sb.toString());
            System.out.println(sepLR);
            System.out.println(sepRL);
            
            // Without a temp variable, swap the outer loop element and smallest element ahead of it
            // Sorted elements propagate from start of array towards end
            arr[i] = arr[i] ^ arr[index];
            arr[index] = arr[i] ^ arr[index];
            arr[i] = arr[i] ^ arr[index];
            
            sb = new StringBuffer("\t ");
            
            for (int k = 0; k < arr.length; k++) {
                sb.append(String.format("%2d", arr[k]) + (k == arr.length - 1 ? "" : ", "));
            }
            
            System.out.println(sb.toString());            
            System.out.println(sbIndex.toString() + "\n");
        }
        
        return;
    }

    public static void main(String a[]) {

        int[] arr = { 10, 34, 2, 56, 7, 67, 88, 42 };
        doSelectionSort(arr);
    }
}
