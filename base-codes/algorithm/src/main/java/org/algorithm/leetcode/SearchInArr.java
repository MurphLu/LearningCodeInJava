package org.algorithm.leetcode;

/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 先通过二分找到数组最大值，然后确定 target 在哪半部分，确定范围，再次二分查找到 target
 */
public class SearchInArr {
    public static void main(String[] args) {
        int search = new SearchInArr().search(
                new int[]{4,5,6,7,0,1,2},
                5
        );
        System.out.println(search);
    }
    public int search(int[] nums, int target) {
        int n = nums.length - 1;
        int last = nums[n];
        if (last == target) {
            return n;
        }
        int edge = edge(nums, 0, n, last);
//        return edge;

        int from = 0;
        int to = n;
        if (last < nums[0]) {
        if (last > target) {
            from = edge+1;
            to = n;
        } else {
            from = 0;
            to = edge;
        }}
        int idx = find(nums, from, to, target);
        return nums[idx] == target ? idx : -1;
    }

    private int find(int[] nums, int from, int to, int target) {
        if (nums[to] == target) {
            return to;
        }
        if (nums[from] == target) {
            return from;
        }
        int mid = (to-from) / 2 + from;
        int numMid = nums[mid];
        if (from < to - 1) {
            if(numMid > target){
                return find(nums, from, mid,target);
            }else {
                return find(nums, mid, to, target);
            }
        }
        return mid;
    }

    private int edge(int[] nums, int from, int to, int i) {
        int mid = (to-from) / 2 + from;
        int numMid = nums[mid];
        if (from < to - 1) {
            if(numMid > i){
                return edge(nums, mid, to, i);
            }else {
                return edge(nums, from, mid, i);
            }
        }
        return mid;
    }
}
