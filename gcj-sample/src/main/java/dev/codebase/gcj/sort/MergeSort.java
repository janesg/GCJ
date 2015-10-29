package dev.codebase.gcj.sort;

import java.util.ArrayList;
import java.util.List;

/*
 * Merge sort is a divide and conquer algorithm.
 * 
 * Steps to implement Merge Sort:
 * 
 * 1) Divide the unsorted array into n partitions, each partition contains 1 element. 
 *    Here the one element is considered as sorted.
 * 2) Repeatedly merge partitioned units to produce new sublists until there is only 
 *    1 sublist remaining. This will be the sorted list at the end.
 *    
 * Merge sort is a fast, stable sorting routine with guaranteed O(n*log(n)) efficiency. 
 * When sorting arrays, merge sort requires additional scratch space proportional to 
 * the size of the input array. Merge sort is relatively simple to code and offers 
 * performance typically only slightly below that of quicksort.
 */
public class MergeSort {

    private int[] array;
    private int[] tempMergArr;
    private int length;

    public static void main(String a[]) {

        int[] inputArr = { 45, 23, 11, 89, 77, 98, 4, 28, -3, 65, 43 };
        
        List<Integer> arrInteger = new ArrayList<Integer>();
        
        // Can't autobox an array of primitive so have to loop and autobox each element
        for (int i = 0; i < inputArr.length; i++) {
            arrInteger.add(inputArr[i]);
        }        
        
        MergeSort mms = new MergeSort();
        mms.sort(inputArr);
        
        for (int i : inputArr) {
            System.out.print(i);
            System.out.print(" ");
        }
        
        System.out.println("\narrInteger prior to mergeSort : " + arrInteger);
        System.out.println("arrInteger after mergeSort : " + mergeSort(arrInteger));
        
    }

    public void sort(int inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.tempMergArr = new int[length];
        doMergeSort(0, length - 1);
    }

    private void doMergeSort(int lowerIndex, int higherIndex) {

        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    private void mergeParts(int lowerIndex, int middle, int higherIndex) {

        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            
            k++;
        }
        
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }
    }
    
    /*
     * Another implementation from Java Programming Interviews Exposed
     * 
     * This one sorts a List rather than an array
     * It returns a new List.
     */
    public static List<Integer> mergeSort(final List<Integer> values) {
        
        System.out.println("*** MergeSort : " + values);
        
        // A single element list will always be 'sorted' so return it
        if (values.size() < 2) {
            return values;
        }
        
        // Split the passed list into 2 halves
        final List<Integer> leftHalf = values.subList(0, values.size() / 2);
        final List<Integer> rightHalf = values.subList(values.size() / 2, values.size());
        
        return merge(mergeSort(leftHalf), mergeSort(rightHalf));
    }

    private static List<Integer> merge(final List<Integer> left, final List<Integer> right) {
        
        int leftPtr = 0;
        int rightPtr = 0;
        
        System.out.println("Merge Left : " + left + " and Right : " + right);
        
        // Created list to return of correct size
        final List<Integer> merged = new ArrayList<>(left.size() + right.size());
        
        // Work through the 2 halves in tandem comparing elements from left and
        // right halves and put the smaller of the 2 into the output array
        while (leftPtr < left.size() && rightPtr < right.size()) {
            if (left.get(leftPtr) < right.get(rightPtr)) {
                merged.add(left.get(leftPtr));
                leftPtr++;
            } else {
                merged.add(right.get(rightPtr));
                rightPtr++;
            }
        }
        
        // If we've reached the end of the right half, just add each 
        // remaining element from the left half...these are not necessarily sorted     
        while (leftPtr < left.size()) {
            merged.add(left.get(leftPtr));
            leftPtr++;
        }

        // If we've reached the end of the left half, just add each 
        // remaining element from the right half...these are not necessarily sorted
        while (rightPtr < right.size()) {
            merged.add(right.get(rightPtr));
            rightPtr++;
        }
        
        System.out.println("Merged list : " + merged);
        
        return merged;
    }
    
}
