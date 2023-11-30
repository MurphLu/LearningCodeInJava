package org.algorithm.leetcode.daily;

/**
 * 给你一个下标从 0 开始的整数数组 nums ，如果满足下述条件，则认为数组 nums 是一个 美丽数组 ：
 *
 * nums.length 为偶数
 * 对所有满足 i % 2 == 0 的下标 i ，nums[i] != nums[i + 1] 均成立
 * 注意，空数组同样认为是美丽数组。
 *
 * 你可以从 nums 中删除任意数量的元素。当你删除一个元素时，被删除元素右侧的所有元素将会向左移动一个单位以填补空缺，而左侧的元素将会保持 不变 。
 *
 * 返回使 nums 变为美丽数组所需删除的 最少 元素数目。
 */
public class MinDeletion {
    public static void main(String[] args) {
        new MinDeletion().minDeletion(
                new int[]{1,1,2,2,3,3}
        );
    }
    public int minDeletion(int[] nums) {
        int count = 0;
        int curIndex = 0;
        int index = 1;
        int curNum = nums[0];
        while (index < nums.length) {
            if (curIndex % 2 == 0 && curNum == nums[index]) {
                count++;
            } else {
                curIndex++;
                curNum = nums[index];
            }
            index ++;
        }
        // 因为元素个数要求为偶数，所以如果为奇数的话需要多删除一个元素
        if ((nums.length - count) % 2 != 0) {
            count++;
        }
        System.out.println(count);
        return count;
    }
}
