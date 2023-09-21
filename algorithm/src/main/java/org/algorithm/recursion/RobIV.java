package org.algorithm.recursion;

import java.util.*;

/**
 * 沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。
 *
 * 由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。
 *
 * 小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。
 *
 * 给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。
 *
 * 另给你一个整数 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。
 *
 * 返回小偷的 最小 窃取能力。
 *
 * --- 取最小金额结果中 金额最大的那个值
 */
public class RobIV {
    public static void main(String[] args) {
        int[] nums = new int[]{5038,3053,2825,3638,4648,3259,4948,4248,4940,2834,109,5224,5097,4708,2162,3438,4152,4134,551,3961,2294,3961,1327,2395,1002,763,4296,3147,5069,2156,572,1261,4272,4158,5186,2543,5055,4735,2325,1206,1019,1257,5048,1563,3507,4269,5328,173,5007,2392,967,2768,86,3401,3667,4406,4487,876,1530,819,1320,883,1101,5317,2305,89,788,1603,3456,5221,1910,3343,4597};
        System.out.println(nums.length);
        System.out.println(new RobIV().minCapability(nums, 28));
    }
    public int minCapability(int[] nums, int k) {
        System.out.println(Arrays.toString(process(nums, 0, k, 0, 0, 0)));
        return 0;
    }

    Integer curResult;

    public int[] process(int[] nums, int cur, int lim, int size, int total, int max) {
        if (cur >= nums.length || size == lim || (((nums.length - cur + 1) / 2) < (lim - size)) || (curResult != null && curResult < total)) {
            if (size == lim) {
                if (curResult == null) {
                    curResult = total;
                } else {
                    curResult = Math.min(curResult, total);
                }
                return new int[]{total, max};
            }
            return new int[]{Integer.MAX_VALUE, 0};
        }
        int[] a = process(nums, cur + 1, lim, size, total, max);
        int[] b = process(nums, cur + 2, lim, size + 1, total + nums[cur], Math.max(nums[cur], max));
        return a[0] < b[0] ? a : b;
    }
}
