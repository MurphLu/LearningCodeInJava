package org.dp.core.strategy;

public class Sorter {

    public static void sort(Comparable<Object>[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minPos = i;
            for (int j = i+1; j < arr.length; j++) {
                minPos = arr[j].compareTo(arr[minPos]) < 0 ? j : minPos;
            }
            swap(arr, i, minPos);
        }
    }

    private static void swap(Comparable<Object>[] arr, int i, int minPos) {
        Comparable<Object> temp = arr[i];
        arr[i] = arr[minPos];
        arr[minPos] = temp;
    }
}
