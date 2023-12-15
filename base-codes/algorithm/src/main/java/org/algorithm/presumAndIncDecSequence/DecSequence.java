package org.algorithm.presumAndIncDecSequence;

import java.util.Arrays;

public class DecSequence {
    int[] arr;
    int[] diff;
    int n;

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 7, 5, 6, 2, 4};
        DecSequence decSequence = new DecSequence(arr);
        decSequence.decSequence();
        decSequence.opt(1, 3, 4);
        decSequence.print();
        decSequence.opt(2,4, -5);
        decSequence.print();
    }

    public DecSequence(int[] nums) {
        this.n = nums.length;
        this.arr = nums;
        this.diff = new int[n + 1];
    }

    public void decSequence() {
        diff[0] = arr[0];
        for (int i = 1; i < n; i++) {
            diff[i] = arr[i] - arr[i - 1];
        }
        System.out.println(Arrays.toString(diff));
    }

    // 对其中某些范围的数加或者减时，直接对差分数组的 from + addon，对 [to+1] - addon，通过差分数组还原之后就是我们要的结果
    public void opt(int from, int to, int addon){
        diff[from] += addon;
        diff[to + 1] -= addon;
    }

    public void print() {
        int[] result = new int[n];
        result[0] = diff[0];
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] + diff[i];
        }
        System.out.println(Arrays.toString(result));
    }
}
