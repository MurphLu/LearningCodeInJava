package org.algorithm.sort.comparable;

import org.algorithm.utils.ArrayUtils;

// 时间复杂度：O(n²)，空间复杂度O(1)
public class SelectionSort implements Sorter{
    public void sort(int[] arr) {
        if (arr==null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            ArrayUtils.swap(arr, i, minIndex);
        }
    }
}
