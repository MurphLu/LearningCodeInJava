package org.algorithm.sort.comparable;

import org.algorithm.utils.ArrayUtils;

// 时间复杂度：O(n²)，空间复杂度O(1)
public class InsertSort implements Sorter {
    @Override
    public void sort(int[] arr) {
        if(!ArrayUtils.canSort(arr)){ return; }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j-1]; j--) {
                ArrayUtils.swap(arr, j, j-1);
            }
        }
    }
}
