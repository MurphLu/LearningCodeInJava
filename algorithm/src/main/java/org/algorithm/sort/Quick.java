package org.algorithm.sort;

import org.algorithm.utils.ArrayUtils;

import java.util.Arrays;

public class Quick implements Sorter {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 9, 5, 3};
        new Quick().sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arr) {
        if(!ArrayUtils.canSort(arr)) {return;}
        ArrayUtils.swapWithTemp(arr, (int)(Math.random()*(arr.length)), arr.length - 1);
        process(arr, 0, arr.length - 1);
    }

    private void process(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }
        int[] partition = partition(arr, start, end);
        process(arr, start, partition[0]);
        process(arr, partition[1], end);
    }

    private int[] partition(int[] arr, int start, int end) {
        int left = start;
        int right = end;
        while (start < right) {
            if (arr[start] < arr[end]) {
                ArrayUtils.swapWithTemp(arr, left++, start++);
            } else if (arr[start] > arr[end]) {
                ArrayUtils.swapWithTemp(arr, start, --right);
            } else {
                start++;
            }
        }
        ArrayUtils.swapWithTemp(arr, right++, end);
        return new int[]{left-1, right};
    }
}
