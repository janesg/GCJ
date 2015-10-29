package dev.codebase.gcj.sandbox;

public class SortSelection {

    public static int[] bubbleSort(int[] array) {
    
        if (array == null || array.length < 2) {
            return array;
        }
        
        for (int i = array.length - 1; i >= 0; i--) {
            int swapCount = 0;
            
            for (int j = 0; j < i; j++) {
                if (array[j] > array [j + 1]) {
                    swap(j, j + 1, array);
                    swapCount++;
                }
            }
            
            if (swapCount == 0) {
                break;
            } else {
                swapCount = 0;
            }
        }
        
        return array;
    }
    
    public static int[] insertionSort(int[] array) {
    
        if (array == null || array.length < 2) {
            return array;
        }
    
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j - 1] > array[j]) {
                    swap(j - 1, j, array);
                }
            }
        }
    
        return array;
    }
    
    
    private static void swap(int idx1, int idx2, int[] array) {
    
        // This in-place swap method only works if values are different
        // ...besides, no need to swap if same
        if (array[idx1] != array[idx2]) {
            array[idx1] = array[idx1] ^ array[idx2];
            array[idx2] = array[idx1] ^ array[idx2];
            array[idx1] = array[idx1] ^ array[idx2];
        }
        
        return;
    }

    public static int[] mergeSort(int[] array) {
        
        if (array == null || array.length < 2) {
            return array;
        }
     
        //System.out.println("Merge Sort (before) -> " + arrayToStr(array));

        merge(array, 0, array.length - 1);
        
        //System.out.println("Merge Sort (after)  -> " + arrayToStr(array));

        return array;
    }
    
    private static void merge(int[] array, int lowIdx, int highIdx) {
        
        if (lowIdx < highIdx) {
            //System.out.println("  [M] Low : " + lowIdx + " / " + highIdx + " : High -> " + arrayToStr(array));
            
            // Find the pivot
            int midIdx = lowIdx + (highIdx - lowIdx) / 2;
            
            // Sort the left side of the array
            merge(array, lowIdx, midIdx);
            
            // Sort the right side of the array
            merge(array, midIdx + 1, highIdx);
            
            // Merge both sides
            mergeParts(array, lowIdx, midIdx, highIdx);
        }
        
    }
    
    private static void mergeParts(int[] array, int lowIdx, int midIdx, int highIdx) {
    
        //System.out.println("    [MP] Low : " + lowIdx + " / " + midIdx + 
        //                   " / " + highIdx + " : High -> " + arrayToStr(array));

        // Allocate temp working array
        int[] tempArray = new int[array.length];
   
        // Copy input array to temp array
        for (int i = lowIdx; i <= highIdx; i++) {
            tempArray[i] = array[i];
        }
        
        int i = lowIdx;
        int j = midIdx + 1;
        int k = lowIdx;
        
        while (i <= midIdx && j <= highIdx) {
            if (tempArray[i] <= tempArray[j]) {
                array[k++] = tempArray[i++];
            } else {
                array[k++] = tempArray[j++];
            }
        }
        
        while (i <= midIdx) {
            array[k++] = tempArray[i++];
        }
    }

    public static int[] quickSort(int[] array) {
        
        if (array == null || array.length < 2) {
            return array;
        }
     
        //System.out.println("Quick Sort (before) -> " + arrayToStr(array));

        quick(array, 0, array.length - 1);
        
        //System.out.println("Quick Sort (after)  -> " + arrayToStr(array));

        return array;
    }
    
    private static void quick(int[] array, int lowIdx, int highIdx) {

        // Set mid index as pivot
        int pivot = lowIdx + (highIdx - lowIdx) / 2;
        int i = lowIdx;
        int j = highIdx;

        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a
             * number from right side which is less then the pivot value. Once
             * the search is done, then we exchange both numbers.
             */
            while (array[i] < array[pivot]) {
                i++;
            }
            
            while (array[j] > array[pivot]) {
                j--;
            }
            
            if (i <= j) {
                swap(i, j, array);
                
                // move index to next position on both sides
                i++;
                j--;
            }
        }
        
        // Call quick() method recursively
        if (i < highIdx) {
            quick(array, i, highIdx);
        }
        
        if (lowIdx < j) {
            quick(array, lowIdx, j);
        }
        
    }

    public static String arrayToStr(int[] array) {
        
        if (array == null) return null;
        
        StringBuilder sb = new StringBuilder("[");
        
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i] + (i == array.length - 1 ? "]" : ", "));
        }
        
        return sb.toString();
    }
    
}