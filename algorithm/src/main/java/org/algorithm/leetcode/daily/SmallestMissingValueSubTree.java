package org.algorithm.leetcode.daily;

import java.util.*;

public class SmallestMissingValueSubTree {
    public static void main(String[] args) {
        int[] parents = new int[10000];
        int[] genes = new int[10000];
        for (int i = 0; i < 10000; i++) {
            parents[i] = i - 1;
            genes[i] = i + 1;
        }
        int[] result = new SmallestMissingValueSubTree().smallestMissingValueSubtree(
                parents, genes);
        System.out.println(Arrays.toString(result));
    }

    static class Node {
        int gen;
        int index;
        List<Node> children;

        public Node(int gen, int index) {
            this.gen = gen;
            this.index = index;
            this.children = new ArrayList<>();
        }
    }
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int[] result = new int[parents.length];
        if (nums[0] == 1) {
            Arrays.fill(result, 1);
            Arrays.sort(nums);
            if (nums[0] > 1) {
                return result;
            }
            for (int i = 0; i < nums.length; i++) {
                if(nums[i] != i + 1) {
                    result[0] = i + 1;
                }
            }
            result[0] = nums.length + 1;
            return result;
        }
        Map<Integer, Node> map = new HashMap<>();
        Node head = new Node(nums[0], 0);
        map.put(0, head);
        for (int i = 1; i < parents.length; i++) {
            map.put(i, new Node(nums[i], i));
        }
        for (int i = 1; i < parents.length; i++) {
            Node parent = map.get(parents[i]);
            parent.children.add(map.get(i));
        }
        process(head, result);
        return result;
    }

    static class ReturnMap {
        Set<Integer> genSet;
        int minVal;

        public ReturnMap(Set<Integer> genSet, int minVal) {
            this.genSet = genSet;
            this.minVal = minVal;
        }
    }

    private ReturnMap process(Node head, int[] result) {
        if (head == null) {
            return new ReturnMap(new HashSet<Integer>(), 1);
        }
        Set<Integer> set = new HashSet<>();
        int minVal = 1;
        for (Node node: head.children) {
            ReturnMap cur = process(node, result);
            minVal = Math.max(cur.minVal, minVal);
            if (set.size() > cur.genSet.size()) {
                set.addAll(cur.genSet);
            } else {
                cur.genSet.addAll(set);
                set = cur.genSet;
            }
        }
        set.add(head.gen);
        while (set.contains(minVal)) {
            minVal++;
        }
        result[head.index] = minVal;
        return new ReturnMap(set, minVal);
    }
}
