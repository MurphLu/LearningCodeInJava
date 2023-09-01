package org.algorithm.questions;

import org.algorithm.utils.ArrayUtils;

import java.util.Arrays;

// 给定一个数，将数组中小于等于该数的数字放左边，大于的放右边
// 时间复杂度 O(N), 空间复杂度 O(1)
public class FlagQuestion {
    public static void main(String[] args) {
        int[] arr = {5,1,2,5,9,8,6,5,4,3,10,1,5,5,5};
        process(arr, 5);
        System.out.println(Arrays.toString(arr));
        process2(arr, 5);
        System.out.println(Arrays.toString(arr));

    }

    public static void process(int[] arr, int num) {
        int bounds = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= num) {
                ArrayUtils.swapWithTemp(arr, bounds++, i);
            }
        }
    }

    // 小于在左边，等于在中间，大于在右边
    public static void process2(int[] arr, int num) {
        int left = 0;
        int right = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < num) {
                ArrayUtils.swapWithTemp(arr, left++, i);
                right++;
            } else if (arr[i] == num) {
                ArrayUtils.swapWithTemp(arr, right++, i);
            }
        }
    }
}
