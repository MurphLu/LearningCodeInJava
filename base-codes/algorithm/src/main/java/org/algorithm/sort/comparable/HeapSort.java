package org.algorithm.sort.comparable;

import org.algorithm.utils.ArrayUtils;

public class HeapSort implements Sorter{
    @Override
    public void sort(int[] arr) {
        // 调整大根堆，从头开始heapInsert n*logN, 或者从尾开始 heapify O(N)，后者效率更高
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }

        for (int i = arr.length - 1; i >= 0 ; i--) {
            heapify(arr, i, arr.length);
        }

        int size = arr.length;
        while (size > 0) {
            ArrayUtils.swapWithTemp(arr, 0, --size);
            heapify(arr, 0, size);
        }
    }

    private void heapInsert(int[] arr, int index) {
        while (index > 0 && arr[index] > arr[(index -1) /2]) {
            ArrayUtils.swapWithTemp(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int largestIndex = left + 1 < size && arr[left] < arr[left + 1] ?  left + 1 : left;
            largestIndex = arr[largestIndex] < arr[index] ? index : largestIndex;
            if(largestIndex == index) {
                break;
            }
            ArrayUtils.swapWithTemp(arr, index, largestIndex);
            index  = largestIndex;
            left = index * 2 + 1;
        }
    }
}
