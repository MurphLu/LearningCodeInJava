package org.algorithm.leetcode;

/**
 * 0, 1, 1
 *    1, 1, 2, 2
 *       1, 2
 *          2, 2
 *
 */
public class Jump {
    public static void main(String[] args) {
        System.out.println(jump(new int[]{1,1,2,1,1}));
    }
    public static int jump(int[] nums) {
        int rightCur = 0;
        int rightLast = nums[0];
        int step = 1;
        for (int i = 1; i < nums.length;) {
            if (rightLast >= nums.length - 1) {
                return step;
            }
            while (i <= rightLast) {
                rightCur = Math.max(i + nums[i], rightCur);
                i++;
            }
            step += 1;
            rightLast = Math.max(rightCur, rightLast);
            rightCur = 0;
        }
        return step;
    }
}
