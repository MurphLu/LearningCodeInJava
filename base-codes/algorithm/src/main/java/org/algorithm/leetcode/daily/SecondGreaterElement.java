package org.algorithm.leetcode.daily;

import org.algorithm.tree.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SecondGreaterElement {

    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        List<Integer> winner = new ArrayList<>();
        List<Integer> loser = new ArrayList<>();
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            LinkedList<Integer> temp = new LinkedList<>();
            while (winner.size() > 0 && nums[winner.get(winner.size() - 1)] < num) {
                temp.addFirst(winner.remove(winner.size() - 1));
            }
            while (loser.size() > 0 && nums[loser.get(loser.size() - 1)] < num) {
                res[loser.remove(loser.size() - 1)] = num;
            }
            loser.addAll(temp);
            winner.add(i);
        }
        return res;
    }
}
