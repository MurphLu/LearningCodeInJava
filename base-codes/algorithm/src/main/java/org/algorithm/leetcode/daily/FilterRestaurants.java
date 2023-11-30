package org.algorithm.leetcode.daily;

import java.util.*;

/**
 * 给你一个餐馆信息数组 restaurants，其中  restaurants[i] = [idi, ratingi, veganFriendlyi, pricei, distancei]。你必须使用以下三个过滤器来过滤这些餐馆信息。
 *
 * 其中素食者友好过滤器 veganFriendly 的值可以为 true 或者 false，
 * 如果为 true 就意味着你应该只包括 veganFriendlyi 为 true 的餐馆，为 false 则意味着可以包括任何餐馆。
 * 此外，我们还有最大价格 maxPrice 和最大距离 maxDistance 两个过滤器，它们分别考虑餐厅的价格因素和距离因素的最大值。
 *
 * 过滤后返回餐馆的 id，按照 rating 从高到低排序。
 * 如果 rating 相同，那么按 id 从高到低排序。
 *
 * 简单起见， veganFriendlyi 和 veganFriendly 为 true 时取值为 1，为 false 时，取值为 0 。
 */

// 以 rating 为比较参数的大根堆，节点有类型为 PriorityQueue 存储餐馆的优先队列，id 大的在前，
// 并且存储一个 <rating, index> 的 map，来保存节点的位置，加速获取指定节点
// 按条件筛选完之后入堆，最后出堆，并获取当前节点存储的餐馆 PriorityQueue 出队列，得到指定顺序的结果
public class FilterRestaurants {
    public static void main(String[] args) {
        int[][] res = new int[][]{new int[]{1,4,1,40,10}, new int[]{2,8,0,50,5}, new int[]{3,8,1,30,4}, new int[]{4,10,0,10,3}, new int[]{5,1,1,15,1}};
        System.out.println(new FilterRestaurants().filterRestaurants(res, 0, 50, 10));
    }
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> result = new ArrayList<>();
        Heap heap = new Heap(restaurants.length);
        for (int[] cur : restaurants) {
            int vegan = cur[2];
            int price = cur[3];
            int dis = cur[4];
            if (vegan>= veganFriendly  && price <= maxPrice && dis <= maxDistance) {
                heap.insert(cur);
            }
        }
        while (heap.isNotEmpty()) {
            int[] res = heap.pop();
            for (int re : res) {
                result.add(re);
            }
        }
        return result;
    }

    static class Heap{
        Node[] arr;
        int size;
        Map<Integer, Integer> ratingPosition;

        public Heap(int maxSize) {
            arr = new Node[maxSize];
            size  = 0;
            ratingPosition = new HashMap<>();
        }

        public void insert(int[] restaurant) {
            int rating = restaurant[1];
            if (ratingPosition.containsKey(rating)) {
                Node node = arr[ratingPosition.get(rating)];
                node.add(restaurant);
            } else {
                Node node = new Node(rating);
                node.add(restaurant);
                insert(node);
            }
        }

        private void insert(Node node) {
            arr[size] = node;
            ratingPosition.put(node.rating, size++);
            int idx = size - 1;
            while (idx > 0 && arr[(idx - 1) / 2].rating < arr[idx].rating) {
                swap(arr, (idx - 1) / 2, idx);
                idx = (idx - 1) / 2;
            }
        }

        private void heapify(int idx, int heapSize) {
            int left = idx * 2 + 1;
            while (left < heapSize) {
                int largeIndex = left + 1 < heapSize && arr[left + 1].rating > arr[left].rating ? left + 1 : left;
                largeIndex = arr[largeIndex].rating > arr[idx].rating ? largeIndex : idx;
                if (idx == largeIndex) {
                    break;
                }
                swap(arr, idx, largeIndex);
                idx = largeIndex;
                left = largeIndex * 2 + 1;
            }
        }

        private void swap(Node[] arr, int idx1, int idx2) {
            Node node1 = arr[idx1];
            arr[idx1] = arr[idx2];
            ratingPosition.put(arr[idx1].rating, idx1);
            arr[idx2] = node1;
            ratingPosition.put(arr[idx2].rating, idx2);
        }

        public int[] pop() {
            Node top = arr[0];
            ratingPosition.remove(top.rating);
            arr[0] = arr[--size];
            ratingPosition.put(arr[0].rating, 0);
            heapify(0, size);
            int[] result = new int[top.queue.size()];
            int idx = 0;
            while (!top.queue.isEmpty()) {
                result[idx++] = top.queue.poll()[0];
            }
            return result;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isNotEmpty() {
            return size > 0;
        }

    }
    static class Node {
        int rating;
        PriorityQueue<int[]> queue;

        public Node(int rating) {
            this.rating = rating;
            queue = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        }

        public void add(int[] res) {
            queue.add(res);
        }
    }
}
