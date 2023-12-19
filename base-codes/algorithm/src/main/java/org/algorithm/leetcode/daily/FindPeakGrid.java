package org.algorithm.leetcode.daily;

import org.algorithm.utils.StringToArray;

import java.util.Arrays;

public class FindPeakGrid {
    public static void main(String[] args) {
        int[][] mat = StringToArray.generateArray("[[10,50,40,30,20],[1,500,2,3,4]]");
        new FindPeakGrid().findPeakGrid(mat);
    }
    public int[] findPeakGrid(int[][] mat) {
        int l = 0, r = mat.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            int maxLocation = findMax(mat[mid]);
            if (mid - 1 >= 0 && mat[mid - 1][maxLocation] > mat[mid][maxLocation]){
                r = mid;
            } else if (mid + 1 < mat.length && mat[mid + 1][maxLocation] > mat[mid][maxLocation]) {
                l = mid + 1;
            } else {
                return new int[]{mid, maxLocation};
            }
        }
        return new int[]{};
    }

    private int findMax(int[] nums) {
        int max = 0;
        int i = 1;
        while (i < nums.length) {
            max = nums[max] < nums[i] ? i : max;
            i ++;
        }
        return max;
    }
}
