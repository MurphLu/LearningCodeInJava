package org.algorithm.questions;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组，几乎有序是指，
 * 如果把数组排好顺序的话，每个元素移动的距离可以不超过k，
 * 并且k相对于数组来说比较小。请选择一个合适的排序算法针对这个数据进行排序。
 */
public class SortWithHeapDistanceLessK {
    public static void main(String[] args) {
        int[] arr = new int[]{2,1,3,4,6,5,8,7};
        new SortWithHeapDistanceLessK().sort(arr, 2);
        System.out.println(Arrays.toString(arr));
    }
    public void sort(int[] arr, int maxDistance) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(); // 小根堆
        int index = 0;
        for (; index < Math.min(arr.length, maxDistance); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
}
