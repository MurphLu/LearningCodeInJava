package org.algorithm.sort.withoutCom;


import org.algorithm.sort.comparable.Sorter;
import org.algorithm.utils.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 职工年龄排序，16 - 90
// 计数排序
public class AgeOrder implements Sorter {
    public static void main(String[] args) {
        int[][] generate = ArrayUtils.generate(3, 16, 90, 100);
        int[] arr1 = generate[0].clone();
        int[] arr2 = generate[0].clone();
        new AgeOrder().sort(arr1, 16,90);
        Arrays.sort(arr2);
        System.out.println(Arrays.equals(arr1, arr2));

    }

    public void sort(int[] arr, int minValue, int maxValue) {
        int[] counts = new int[maxValue - minValue + 1];
        for (int i = 0; i < arr.length; i++) {
            counts[arr[i] - minValue] ++;
        }
        System.out.println(Arrays.toString(counts));
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            if(counts[i] == 0) { continue; }
            int c = counts[i];
            while (c > 0) {
                arr[index++] = i+minValue;
                c--;
            }
        }
    }

    @Override
    public void sort(int[] arr) {

    }
}
