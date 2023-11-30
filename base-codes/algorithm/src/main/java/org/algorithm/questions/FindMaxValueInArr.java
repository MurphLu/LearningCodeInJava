package org.algorithm.questions;


// T(N) =       a       *   T(N/b) + O(Nᵈ)
// 母问题  子问题调用次数      等分子问题  除去等分部分时间复杂度

public class FindMaxValueInArr {
    public static void main(String[] args) {
        int[] arr = {1,2,9,4,5,6,6,7,4,8};
        findMax(arr);
    }
    public static void findMax(int[] arr){
        int index = splitAndCompare(arr, 0, arr.length-1);
        System.out.println(arr[index]);
    }

    public static int splitAndCompare(int[] arr, int start, int end) {
        if(start == end) { return start; }
        int mid = start + (end-start)/2; // 防溢出  (end-start)/2 == (end-start)>>1
        int leftMaxIndex = splitAndCompare(arr, start, mid);
        int rightMaxIndex = splitAndCompare(arr, mid + 1, end);
        return arr[leftMaxIndex] < arr[rightMaxIndex] ? rightMaxIndex : leftMaxIndex;
    }
}
