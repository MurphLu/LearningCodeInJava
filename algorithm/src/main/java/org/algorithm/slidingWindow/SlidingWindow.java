package org.algorithm.slidingWindow;

/**
 * 由一个代表题目，引出一种结构
 * [题目]有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次 向右边滑一个位置。
 * 例如，数组为[4,3,5,4,3,3,6,7]，窗口大小为3时:
 * [4 3 5]4 3 3 6 7
 * 4[3 5 4]3 3 6 7
 * 4 3[5 4 3]3 6 7
 * 4 3 5[4 3 3]6 7
 * 4 3 5 4[3 3 6]7
 * 4 3 5 4 3[3 6 7]
 * 窗口中最大值为5 窗口中最大值为5 窗口中最大值为5 窗口中最大值为4 窗口中最大值为6窗口中最大值为7
 * 如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值。
 * 请实现一个函数。 输入:整型数组arr，窗口长小为w。
 * 输出:一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的 以本题为例，结果应该
 * 返回{5,5,5,4,6,7}。
 */
public class SlidingWindow {

    public static void main(String[] args) {
        int[] arr = new int[]{4,3,5,4,3,3,6,7};
        new SlidingWindow().slidingWindow(arr, 3);

    }

    public void slidingWindow(int[] arr, int size) {
        Deque<Integer> deque = new Deque<>();
        int i = 0;
        while (i < size) {
            pushTail(deque, arr, i++);
        }
        System.out.println(arr[getHead(deque)]);
        while (i < arr.length) {
            pop(deque, i - size);
            pushTail(deque, arr, i++);
            System.out.println(arr[getHead(deque)]);
        }
    }

    public void pushTail(Deque<Integer> deque, int[] arr, Integer idx) {
        while (deque.isNotEmpty() && arr[deque.getTail()] <= arr[idx]) {
            deque.popTail();
        }
        deque.pushTail(idx);
    }

    public void pop(Deque<Integer> deque, int idx) {
        if (deque.getHead() == idx) {
            deque.popHead();
        }
    }

    public Integer getHead(Deque<Integer> deque) {
        return deque.getHead();
    }
}
