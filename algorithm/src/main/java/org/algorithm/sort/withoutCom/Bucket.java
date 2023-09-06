package org.algorithm.sort.withoutCom;

import org.algorithm.utils.ArrayUtils;

import java.util.*;

// 基数排序, 有进制，有限个桶
public class Bucket {

    public static void main(String[] args) {
        int[][] generate = ArrayUtils.generate(100, 0, 1000, 50);
        for (int[] arr: generate) {
            int[] arr1 = arr.clone();
            int[] arr2 = arr.clone();
            sort(arr1);
            Arrays.sort(arr2);
            boolean equals = Arrays.equals(arr1, arr2);
            System.out.println(equals);
        }
    }

    public static void sort(int[] arr) {
        int digit = 0;
        for (int i = 0; i <arr.length; i++) {
            int d = 0;
            int v = arr[i];
            while (v>0){
                v/=10;
                d++;
            }
            digit = Math.max(digit, d);
        }
        radixSort(arr, 0, arr.length - 1, digit);
    }

    public static void radixSort(int[] arr, int start, int end, int digit) {
        final int radix = 10;
        int[] bucket = new int[end - start + 1];

        // 从低位到高位排序
        for (int i = 1; i <= digit; i++) {
            int[] collect = new int[radix];
            // 统计当前位各个数字出现的次数
            for (int j = start; j <= end; j++) {
                int value = arr[j];
                int d = getDigit(value, i);
                collect[d]++;
            }
            // 确定当前位每个数开始的位置
            int[] position = new int[radix];
            position[0] = 0;
            for (int j = 0; j < collect.length ; j++) {
                if (j == 0) { continue; }
                if(j == 1) { position[j] = collect[j-1]; }
                position[j] = position[j-1] + collect[j-1];
            }
            // 将 arr 按照当前位入桶
            for (int j = start; j <= end; j++) {
                int value = arr[j];
                int d = getDigit(value, i);
                bucket[position[d]++] = arr[j];
            }
            // 出桶，继续比较高一位
            for (int j = 0; j < bucket.length; j++) {
                arr[j] = bucket[j];
            }
        }
    }

    private static int getDigit(int num, int d) {
        return num % (int)Math.pow(10, d) / (int)Math.pow(10, d-1);
    }
}
