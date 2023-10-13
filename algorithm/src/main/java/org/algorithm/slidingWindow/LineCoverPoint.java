package org.algorithm.slidingWindow;

/**
 * 给定一个有序数组arr，代表数轴上从左到右有n个点
 * arr[o]、arr[1]...arr[n-1]
 * 给定一个正数L，代表一根长度为L的绳子，求绳子最多能覆盖其中的几个点。
 */
public class LineCoverPoint {
    public static void main(String[] args) {

    }

    public static int cover(int[] nums, int L) {
        int[] cover = new int[nums.length];
        int left = 0;
        int right = 0;
        int count = 1;
        while (left <= right && right < nums.length - 1) {
            if (nums[right + 1] - nums[left] <= L) {
                right++;
                count +=1;
            } else {
                cover[left] = count;
                if (left < right) {
                    left++;
                    count--;
                } else {
                    left ++;
                    right++;
                    count = 1;
                }
            }
        }
        cover[left] = count;
        int result = 0;
        for (int j : cover) {
            result = Math.max(result, j);
        }
        return result;
    }
}
