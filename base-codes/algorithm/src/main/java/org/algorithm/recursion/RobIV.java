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
 *
 */
public class RobIV {
    public static void main(String[] args) {
        int[] nums = new int[]{2,2};
//        System.out.println(nums.length);
//        System.out.println(new RobIV().minCapability(nums, 28));
        System.out.println(new RobIV().process3(nums, 1));
    }
    public int minCapability(int[] nums, int k) {
        System.out.println(Arrays.toString(process(nums, 0, k, 0, 0, 0)));
        return 0;
    }

    Integer curResult;

    // 超时。。。。
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

    /**
     * 二分加 dp
     * @param nums
     * @param k
     * @return
     */
    public int process3(int[] nums, int k) {

        // 获取数组中的最小值和最大值，进行二分
        int min = 0;
        int max = Arrays.stream(nums).max().getAsInt();

        while (min < max) {
            // 获取中间值，接下来判断，如果取中间值的时候，最多可以偷多少家
            int guess = min + (max - min) / 2;
            if (check(nums, guess, k)) { // 如果可偷的数量大于 k, 那么当前的 guess 是可以偷到 k 家以上的
                // 那么设置 max = guess 进行下一轮判断，取 min 和 guess 的中间值判断
                max = guess;
            } else { // 如果基于当前 guess 作为盗窃能力最大偷不到 k 家，那么说明 guess <= 的值不满足
                // 取 guess 和 max 中间值判断
                min = guess + 1;
            }
        }
        return min;
    }

    // 基于 minCap 能力最多可以偷多少家，并返回是否大于 k
    public boolean check(int[] nums, int minCap, int k) {
        // 新建一个长度为 nums.length 的数组，用来存储最多可以偷多少家
        int[] dp = new int[nums.length];
        int cur = 0;
        // 如果前 cur 家的金额都大于能力，那么将 dp 的前 cur 个值都设置为 0，因为基于当前能力前 cur 家不能偷
        while (cur < dp.length && nums[cur] > minCap) {
            dp[cur++] = 0;
        }
        // 如果一直遍历到了数组最后都没有满足的，那么都不能偷，直接返回 false
        if (cur == nums.length) {
            return false;
        }
        // 设置第一个金额小于盗窃能力的位置为可偷
        dp[cur ++] = 1;
        // 从下一家开始
        for (int i = cur; i < nums.length; i++) {
            // 如果当前位置为可以偷的第二家，又因为不可以连续偷两家，那么当前位置直接设置为 1，表示只能偷当前家或者上一家
            if (i - 2 < 0 || dp[i - 2] == 0) {
                dp[i] = 1;
                continue;
            }
            // 又如果当前位置的金额大于盗窃能力，那么直接跳过，到当前位置最大可以偷的数量不变，直接为到前一家为止最大可以偷的数量
            if (nums[i] > minCap) {
                dp[i] = dp[i - 1];
                continue;
            }
            // 判断偷当前位置或者不偷当前位置，哪种情况可以偷的数量最多，并将最大值设置为到当前位置为止最多可以偷多少家
            dp[i] = Math.max(dp[i - 2] + 1, dp[i - 1]);
        }
        // 返回是否满足
        return dp[nums.length - 1] >= k;
    }
}
