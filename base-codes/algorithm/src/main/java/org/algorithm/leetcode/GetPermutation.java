package org.algorithm.leetcode;

import java.util.Arrays;

/**
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 */
public class GetPermutation {
    public static void main(String[] args) {
        int[] size = new int[10];
        size[0] = 1;
        for (int i = 1; i <= 9; i++) {
            size[i] = size[i-1] * i;
        }
        System.out.println(Arrays.toString(size));
        String permutation = new GetPermutation().getPermutation(3, 4);
        System.out.println(permutation);
    }
    // "123456789"
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <=n ; i++) {
            sb.append(i);
        }
        String s = sb.toString();
        int[] size = new int[10];
        size[0] = 1;
        for (int i = 1; i <= 9; i++) {
            size[i] = size[i-1] * i;
        }
        return getPermutation(sb, k, size);
    }

    public String getPermutation(StringBuilder sb, int k, int[] size) {
        StringBuilder res = new StringBuilder();
        if (sb.length() == 1) {
            return sb.toString();
        }
        int sizeAfterPickOne = size[sb.length() - 1];
        if (size[sb.length() - 1] > k) {
            res.append(sb.charAt(0));
            res.append(getPermutation(sb.deleteCharAt(0), k, size));
            return res.toString();
        }
        int pickIdx = 0;
        while (sizeAfterPickOne * (pickIdx + 1) < k) {
            pickIdx++;
        }
        res.append(sb.charAt(pickIdx));
        res.append(getPermutation(sb.deleteCharAt(pickIdx),k-sizeAfterPickOne*pickIdx, size));

        return res.toString();
    }
}
