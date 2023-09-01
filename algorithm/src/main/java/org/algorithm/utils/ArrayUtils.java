package org.algorithm.utils;

import java.util.Arrays;

public class ArrayUtils {
    public static void main(String[] args) {
        int[][] arr = generate(10, 100, 100);
        System.out.println(Arrays.deepToString(arr));
    }
    public static boolean canSort(int[] arr) {
        return arr != null && arr.length >= 2;
    }
    public static void swap(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void swapWithTemp(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

    public static int[][] generate(int caseNum, int maxValue, int maxSize) {
        int[][] arr = new int[caseNum][];
        for (int i = 0; i < caseNum; i++) {
            arr[i] = arrGenerator(maxValue, maxSize);
        }
        return arr;
    }

    private static int[] arrGenerator(int maxValue, int maxSize) {
        int size = (int)((maxSize + 1) * Math.random());
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int)((maxValue + 1) * Math.random());
        }
        return arr;
    }
}
