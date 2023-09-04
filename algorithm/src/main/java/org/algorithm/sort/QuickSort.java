package org.algorithm.sort;

import org.algorithm.utils.ArrayUtils;

import java.util.Arrays;

public class QuickSort implements Sorter{
    @Override
    public void sort(int[] arr) {
        if(!ArrayUtils.canSort(arr)) {return;};
        ArrayUtils.swapWithTemp(arr, (int)(Math.random()*(arr.length)), arr.length - 1);
        process(arr, 0, arr.length - 1);
    }

    private void process(int[] arr, int start, int end) {
        if(start > end) {
            return;
        }
        int[] p = partition(arr, start, end);
        process(arr, start, p[0]-1);
        process(arr, p[1]+1, end);
    }


    private int[] partition(int[] arr, int start, int end){
        int left = start - 1;
        int right = end;
        while (start < right) {
            if(arr[start]<arr[end]) {
                ArrayUtils.swapWithTemp(arr, ++left, start++);
            } else if(arr[start] > arr[end]) {
                ArrayUtils.swapWithTemp(arr, --right, start);
            }else {
                start++;
            }

        }
        ArrayUtils.swapWithTemp(arr, right, end);
        return new int[]{left + 1, right};
    }

}

