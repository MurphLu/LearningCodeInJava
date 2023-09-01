package org.algorithm.questions;

// 归并排序扩展
// 小和问题
// 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和

import org.algorithm.utils.ArrayUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MinimumSum {
    public static void main(String[] args) {
        calc();
    }
    public static void calc() {
        int[][] generate = ArrayUtils.generate(100, 1000, 1000);
        for(int[] arr: generate) {
            int[] arr1 = arr.clone();
            int[] arr2 = arr.clone();
            System.out.println(new MinimumSum().forP(arr1));
            System.out.println(new MinimumSum().process(arr2, 0, arr2.length - 1));
        }
//        int[] arr = {1,3,4,2,5};
//        System.out.println(new MinimumSum().forP(arr));
//        System.out.println(new MinimumSum().process(arr, 0, arr.length - 1));
    }

    private int forP(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int times = 0;
            for (int j = i; j < arr.length; j++) {
                if(arr[i] < arr[j]) {
                    times += 1;
                }
            }
            sum += arr[i] * times;
        }
        return sum;
    }

    private int process(int[] arr, int start, int end) {
        if (start == end) { return 0; }
        int mid = start + (end - start) / 2;
        int l = process(arr, start, mid);
        int r = process(arr, mid + 1, end);
        return merge(arr, start, mid, end) + l + r;
    }

    private int merge(int[] arr, int start, int mid, int end) {
        int sum = 0;
        int[] temp = new int[end - start + 1];
        int i = 0;
        int l = start;
        int r = mid + 1;
        while (i < temp.length) {
            while (l <= mid && r <= end) {
                int t;
                if(arr[l]<arr[r]) {
                    t = arr[l++];
                    sum += t * (end - r + 1);
                }else {
                    t = arr[r++];
                }
                temp[i++] = t;
            }
            while(l <= mid) {
                temp[i++] = arr[l++];
            }
            while (r<=end) {
                temp[i++] = arr[r++];
            }
        }
        System.arraycopy(temp, 0, arr, start, temp.length);

        return sum;
    }
}
