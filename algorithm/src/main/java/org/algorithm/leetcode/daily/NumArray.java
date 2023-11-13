package org.algorithm.leetcode.daily;

public class NumArray {
    public static void main(String[] args) {
        for (int i = 1; i <= 16; i++) {
            System.out.println(i + " " + (i + (i & -i)));
            System.out.println(i + " " + (i & i - 1));
        }
    }
    private int[] arr;

    public NumArray(int[] nums) {
        this.arr = nums;
    }

    public void update(int index, int val) {
        this.arr[index] = val;
    }

    public int sumRange(int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += arr[i];
        }
        return sum;
    }
}

class FenwickTree {
    private final int[] arr;
    private final int[] bit;

    public FenwickTree(int[] nums) {
        this.arr = nums;
        this.bit = new int[nums.length + 1];
        for (int i = 1; i < this.bit.length; i++) {
            updateTree(i, this.arr[i - 1]);
        }
    }

    private void updateTree(int index, int val) {
        while (index <= this.arr.length) {
            this.bit[index] += val;
            index += (index & -index);
        }
    }

    public void update(int index, int val) {
        int temp = arr[index];
        int delta = val - temp;
        arr[index] = val;
        updateTree(index + 1, delta);
    }

    private int prefixSum(int index) {
        int res = 0;
        while (index > 0) {
            res += this.bit[index];
            index -= (index & -index);
        }
        return res;
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }
}
