package org.algorithm.leetcode.daily;

import java.util.List;

public class SumIndicesWithKSetBits {

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(new SumIndicesWithKSetBits().bitCount(i));
//        }
        int i = 2;
        while (i!= 0 && (i&1) == 1) {
            System.out.println(i);
            i = i>>1;
        }
    }
    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.size(); i++) {
            int locBitCount = bitCount(i);
            if (locBitCount == k) {
                res += nums.get(i);
            }
        }
        return res;
    }

    private int bitCount(int n) {
        int count = 0;
        while (n != 0) {
            if((n & 1) == 1){
                count += 1;
            }
            n >>= 1;
        }
        return count;
    }
}
