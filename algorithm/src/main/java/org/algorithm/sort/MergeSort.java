package org.algorithm.sort;

import org.algorithm.utils.ArrayUtils;

public class MergeSort implements Sorter{
    @Override
    public void sort(int[] arr) {
        if(!ArrayUtils.canSort(arr)) { return; }
        process(arr, 0, arr.length - 1);
    }

    private void process(int[] arr, int start, int end) {
        if (start == end) { return; }
        int mid = start + (end - start) / 2;
        process(arr, start, mid);
        process(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    private void merge(int[] arr, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = 0;
        int l = start;
        int r = mid + 1;
        while (i < temp.length) {
            if (l <= mid && r <= end) {
                temp[i++] = arr[l] < arr[r] ? arr[l++] : arr[r++];
                continue;
            }
            if(l <= mid) {
                temp[i++] = arr[l++];
            }
            if(r<=end) {
                temp[i++] = arr[r++];
            }
        }
        System.arraycopy(temp, 0, arr, start, temp.length);
    }
}
