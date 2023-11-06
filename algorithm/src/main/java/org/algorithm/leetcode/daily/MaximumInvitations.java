package org.algorithm.leetcode.daily;

import java.util.*;

// 一定有环，可能有多个组
// 环的长度大于 2，那么就取环的长度
// 如果换的长度等于2，则取到环上两个节点最长距离之和
public class MaximumInvitations {
    public static void main(String[] args) {
        System.out.println(maximumInvitations(new int[]{6,4,4,5,0,3,3}));
        // 1,0,0,2,1,4,7,8,9,6,7,  10, 8
        // 0 1 2 3 4 5 6 7 8 9 10 11   12
    }


    static class TwoPointCircleList {
        static class Node {
            int val;
            List<Node> next;
            int distance;

            public Node(int val, int distance) {
                this.val = val;
                this.distance = distance;
                next = new ArrayList<>();
            }
        }
        Node pointOne;
        Node pointTwo;

        Map<Integer, Node> edgeOneNodeMap = new HashMap<>();
        Map<Integer, Node> edgeTwoNodeMap = new HashMap<>();
        int maxDisToOne;
        int maxDisToTwo;

        public TwoPointCircleList(int pointOne, int pointTwo) {
            this.pointOne = new Node(pointOne, 1);
            this.pointTwo = new Node(pointTwo, 1);
            edgeOneNodeMap.put(pointOne, this.pointOne);
            edgeTwoNodeMap.put(pointTwo, this.pointTwo);
            maxDisToOne = 1;
            maxDisToTwo = 1;
        }

        boolean checkAndAdd(List<Integer> map, int pointNeedToCheck) {
            if (edgeOneNodeMap.containsKey(pointNeedToCheck)) {
                maxDisToOne = Math.max(maxDisToOne, add(edgeOneNodeMap, map, pointNeedToCheck));
                return true;
            } else if (edgeTwoNodeMap.containsKey(pointNeedToCheck)) {
                maxDisToTwo = Math.max(maxDisToTwo, add(edgeTwoNodeMap, map, pointNeedToCheck));
                return true;
            } else {
                return false;
            }
        }

        private int add(Map<Integer, Node> edge, List<Integer> points, int pointNeedToCheck) {
            Node node = edge.get(pointNeedToCheck);
            for (int i = points.size() - 1; i >= 0; i--) {
                Node newNode = new Node(points.get(i), node.distance + 1);
                node.next.add(newNode);
                edge.put(points.get(i), newNode);
                node = newNode;
            }
            return node.distance;
        }

        public int maxDistance() {
            return this.maxDisToTwo + this.maxDisToOne;
        }
    }

    public static int maximumInvitations(int[] favorite) {
        Set<Integer> checked = new HashSet<>();
        List<TwoPointCircleList> twoList = getList(favorite, checked);
        int max = 0;
        for (int i = 0; i < favorite.length; i++) {
            int temp = i;
            if (checked.contains(temp)) {
                continue;
            }
            Map<Integer, Integer> map = new HashMap<>();
            List<Integer> list = new ArrayList<>();
            int index = 0;
            while (!map.containsKey(temp) && !checked.contains(temp)) {
                map.put(temp, index++);
                list.add(temp);
                temp = favorite[temp];
                boolean addToTwo =false;
                for (TwoPointCircleList twoPoint: twoList) {
                    if (twoPoint.checkAndAdd(list, temp)) {
                        addToTwo = true;
                    }
                }
                if (addToTwo) {break;}
            }
            if (map.containsKey(temp)) {
                max = Math.max(index - map.get(temp), max);
            }

            checked.addAll(map.keySet());

        }
        int twoMax = 0;
        for (TwoPointCircleList t: twoList
             ) {
            twoMax += t.maxDistance();
        }
        return Math.max(twoMax, max);
    }

    private static List<TwoPointCircleList> getList(int[] favorite, Set<Integer> checked) {
        List<TwoPointCircleList> lists = new ArrayList<>();
        for (int i = 0; i < favorite.length; i++) {
            if (checked.contains(i)) {continue;}
            if (i == favorite[favorite[i]]) {
                lists.add(new TwoPointCircleList(i, favorite[i]));
                checked.add(i);
                checked.add(favorite[i]);
            }
        }
        return lists;
    }
}
