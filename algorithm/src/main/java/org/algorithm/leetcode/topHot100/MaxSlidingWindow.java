package org.algorithm.leetcode.topHot100;

import java.util.*;

public class MaxSlidingWindow {

    static class Heap {
        private Map<Integer, Integer> map;
        private int[] heap;
        private int[] source;
        int size;

        public Heap(int cap, int[] source) {
            this.source = source;
            this.heap = new int[cap];
            this.size = 0;
            this.map = new HashMap<>();
        }

        public void add(int srcIndex) {
            heap[size] = srcIndex;
            map.put(srcIndex, size);
//            int curPosition = size;
//            int fIndex = (curPosition - 1) / 2;
//            while (curPosition > 0 && source[srcIndex] > source[heap[fIndex]]){
//                swap(curPosition, fIndex);
//                curPosition = fIndex;
//                fIndex = (curPosition - 1) / 2;
//            }
            rebuild(srcIndex, size);
            size++;
        }

        public void remove(int srcIndex) {
            int heapIndex = map.get(srcIndex);
            swap(heapIndex, --size);

            rebuild(heap[heapIndex], heapIndex);
            map.remove(srcIndex);
        }

        private void rebuild(int srcIndex, int heapIndex) {
            int heapFIndex = (heapIndex - 1) / 2;
            if (heapIndex > 0 && source[heap[heapFIndex]] < source[srcIndex]) {
                while (heapIndex > 0 && source[heap[heapFIndex]] < source[srcIndex]) {
                    swap(heapIndex, heapFIndex);
                    heapIndex = heapFIndex;
                    heapFIndex = (heapIndex - 1) / 2;
                }
            } else {
                while (heapIndex < size) {
                    int heapLeft = 2 * heapIndex + 1;
                    int heapRight = 2 * heapIndex + 2;
                    int maxIndex = heapIndex;

                    if (heapLeft < size) {
                        maxIndex = source[heap[heapLeft]] > source[heap[maxIndex]] ? heapLeft : maxIndex;
                    }
                    maxIndex = heapRight < size && source[heap[heapRight]] > source[heap[maxIndex]] ? heapRight : maxIndex;
                    if (maxIndex == heapIndex) {
                        break;
                    }
                    swap(maxIndex, heapIndex);
                    heapIndex = maxIndex;
                }
            }
        }

        public int peek() {
            return source[heap[0]];
        }

        private void swap(int idx1, int idx2) {
            int temp = this.heap[idx1];
            this.heap[idx1] = this.heap[idx2];
            this.heap[idx2] = temp;
            map.put(heap[idx1], idx1);
            map.put(heap[idx2], idx2);
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Heap heap = new Heap(k, nums);
        for (int i = 0; i < k; i++) {
            heap.add(i);
        }
        result[0] = heap.peek();
        int i = 0;

        while (i < nums.length - k) {
            heap.remove(i++);
            heap.add(i + k - 1);
            if (heap.peek() == 4567 || heap.peek() == 8740) {
                System.out.println();
            }
            result[i] = heap.peek();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-5769,-7887,-5709,4600,-7919,9807,1303,-2644,1144,-6410,-7159,-2041,9059,-663,4612,-257,2870,-6646,8161,3380,6823,1871,-4030,-1758,4834,-5317,6218,-4105,6869,8595,8718,-4141,-3893,-4259,-3440,-5426,9766,-5396,-7824,-3941,4600,-1485,-1486,-4530,-1636,-2088,-5295,-5383,5786,-9489,3180,-4575,-7043,-2153,1123,1750,-1347,-4299,-4401,-7772,5872,6144,-4953,-9934,8507,951,-8828,-5942,-3499,-174,7629,5877,3338,8899,4223,-8068,3775,7954,8740,4567,6280,-7687,-4811,-8094,2209,-4476,-8328,2385,-2156,7028,-3864,7272,-1199,-1397,1581,-9635,9087,-6262,-3061,-6083,-2825,-8574,5534,4006,-2691,6699,7558,-453,3492,3416,2218,7537,8854,-3321,-5489,-945,1302,-7176,-9201,-9588,-140,1369,3322,-7320,-8426,-8446,-2475,8243,-3324,8993,8315,2863,-7580,-7949,4400};
        int k = 6;
        int[] result = new MaxSlidingWindow().maxSlidingWindow(nums, k);
        int[] target = new int[]{9807,9807,9807,9807,9807,9807,1303,9059,9059,9059,9059,9059,9059,8161,8161,8161,8161,8161,8161,6823,6823,6218,6218,6869,8595,8718,8718,8718,8718,8718,8718,9766,9766,9766,9766,9766,9766,4600,4600,4600,4600,-1485,-1486,5786,5786,5786,5786,5786,5786,3180,3180,1750,1750,1750,1750,5872,6144,6144,6144,8507,8507,8507,8507,8507,8507,7629,7629,7629,8899,8899,8899,8899,8899,8899,8740,8740,8740,8740,8740,6280,6280,2209,2385,2385,7028,7028,7272,7272,7272,7272,7272,9087,9087,9087,9087,9087,9087,5534,5534,5534,6699,7558,7558,7558,7558,7558,7558,8854,8854,8854,8854,8854,8854,1302,1302,1302,1369,3322,3322,3322,3322,3322,8243,8243,8993,8993,8993,8993,8993,8993};
        for (int i = 0; i < result.length; i++) {
            if (result[i] != target[i]) {
                System.out.println(i + " " + result[i] + " " + target[i]);
            }
        }
        System.out.println(Arrays.toString(new MaxSlidingWindow().maxSlidingWindow(nums, k)));
    }
    //[9807,9807,9807,9807,9807,9807,1303,9059,9059,9059,9059,9059,9059,8161,8161,8161,8161,8161,8161,6823,6823,6218,6218,6869,8595,8718,8718,8718,8718,8718,8718,9766,9766,9766,9766,9766,9766,4600,4600,4600,4600,-1485,-1486,5786,5786,5786,5786,5786,5786,3180,3180,1750,1750,1750,1750,5872,6144,6144,6144,8507,8507,8507,8507,8507,8507,7629,7629,7629,8899,8899,8899,8899,8899,8899,8740,8740,8740,8740,8740,4567,6280,2209,2385,2385,7028,7028,7272,7272,7272,7272,7272,9087,9087,9087,9087,9087,9087,5534,5534,5534,6699,7558,7558,7558,7558,7558,7558,8854,8854,8854,8854,8854,8854,1302,1302,1302,1369,3322,3322,3322,3322,3322,8243,8243,8993,8993,8993,8993,8993,8993]
    //[9807,9807,9807,9807,9807,9807,1303,9059,9059,9059,9059,9059,9059,8161,8161,8161,8161,8161,8161,6823,6823,6218,6218,6869,8595,8718,8718,8718,8718,8718,8718,9766,9766,9766,9766,9766,9766,4600,4600,4600,4600,-1485,-1486,5786,5786,5786,5786,5786,5786,3180,3180,1750,1750,1750,1750,5872,6144,6144,6144,8507,8507,8507,8507,8507,8507,7629,7629,7629,8899,8899,8899,8899,8899,8899,8740,8740,8740,8740,8740,6280,6280,2209,2385,2385,7028,7028,7272,7272,7272,7272,7272,9087,9087,9087,9087,9087,9087,5534,5534,5534,6699,7558,7558,7558,7558,7558,7558,8854,8854,8854,8854,8854,8854,1302,1302,1302,1369,3322,3322,3322,3322,3322,8243,8243,8993,8993,8993,8993,8993,8993]
}