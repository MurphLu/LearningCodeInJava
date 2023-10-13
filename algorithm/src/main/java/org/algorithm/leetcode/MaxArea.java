package org.algorithm.leetcode;

public class MaxArea {
    public static void main(String[] args) {
        System.out.println(new MaxArea().maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int result = Integer.MIN_VALUE;
        while (left < right) {
            result = Math.max(Math.min(height[left], height[right]) * (right - left), result);
            int i = height[left] < height[right] ? left++ : right--;
        }
        return result;
    }
}
