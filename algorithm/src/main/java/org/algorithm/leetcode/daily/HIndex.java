package org.algorithm.leetcode.daily;

public class HIndex {
    public static void main(String[] args) {
        int[] citations = new int[]{5,5,7,7,9,10};
        System.out.println(hIndex(citations));
    }
    public static int hIndex(int[] citations) {
        int c = process(citations);
        if (c == -1) {
            return 0;
        }
        return citations.length - c;
    }

    private static int process(int[] citations) {
        int l = 0;
        int r = citations.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (citations[mid] >= citations.length - mid) {
                if (mid == 0 || citations[mid - 1] < (citations.length - mid + 1)){
                    return mid;
                } else {
                    r = mid - 1;
                }
            } else {
                l = mid - 1;
            }
        }
        return -1;
    }

}
