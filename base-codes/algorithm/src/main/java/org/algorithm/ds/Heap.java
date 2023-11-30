package org.algorithm.ds;

import org.algorithm.utils.ArrayUtils;

import java.util.Arrays;

// 大根堆
public class Heap {
    private int[] arr = new int[10];
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void push(int value) {
        if (arr.length == size) {
            int[] newArr = new int[arr.length * 2];
            System.arraycopy(arr, 0, newArr, 0, size);
            arr = newArr;
        }
        arr[size++]  = value;
        heapInsert(size - 1);
    }

    private void heapInsert(int index) {
        while (index > 0 && arr[index] > arr[(index - 1) / 2]) {
            ArrayUtils.swapWithTemp(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public int pop() {
        int temp = arr[0];
        ArrayUtils.swapWithTemp(arr, --size, 0);
        heapify(0, size);
        return temp;
    }

    private void heapify(int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largestIdx = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largestIdx = arr[index] < arr[largestIdx] ? largestIdx : index;
            if(index == largestIdx) {
                break;
            }
            ArrayUtils.swap(arr, index, largestIdx);
            index = largestIdx;
            left = largestIdx * 2 + 1;
        }
    }

    public void print() {
        System.out.println(Arrays.toString(arr));
    }
}
