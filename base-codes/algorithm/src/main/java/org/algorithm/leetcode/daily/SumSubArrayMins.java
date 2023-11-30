package org.algorithm.leetcode.daily;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

// 3, 4 * 1 + 2, 2 * 2, 4

public class SumSubArrayMins {
    public static void main(String[] args) throws IOException {
        int[] arr = LoadArrFromFile.loadFile();
        int[] arr1 = new int[]{9,9,4,2,5,2,4,5,6,7};
        SumSubArrayMins s = new SumSubArrayMins();
        System.out.println(s.sumSubarrayMins(arr1));
        s.withStack(arr1);
    }
    public int sumSubarrayMins(int[] arr) {
        int res = 0;
        int mod = 1000000007;

        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            for (int j = i; j < arr.length; j++) {
                min = Math.min(min, arr[j]);
                res += min %= mod;
            }
        }
        return res;
    }

    public int withStack(int[] arr) {
        int mod = 1000000007;
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            left[i] = i - (stack.isEmpty() ? -1 : stack.peek());
            stack.add(i);
        }
        stack = new Stack<>();
        for (int i = arr.length - 1; i >=0 ; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            right[i] = (stack.isEmpty() ? arr.length : stack.peek()) - i;
            stack.add(i);
        }
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans  = (ans + (long)left[i] * right[i] * arr[i]) % mod;
        }
        return (int)ans;
    }

    public int withStackOneWay(int[] arr) {
        int mod = 1000000007;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        long sum = 0;
        for (int i = 1; i < arr.length; i++) {
            if (stack.isEmpty() || arr[stack.peek()] <= arr[i]) {
                stack.add(i);
            } else {
                while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                    int index = stack.pop();
                    int left = index - (stack.isEmpty() ? 0 : stack.peek() + 1);
                    int right = i - 1 - index;
                    sum = (sum + (long)(left + right + left * right + 1) * arr[index]) % mod;
                }
                stack.add(i);
            }
        }
        int edge = stack.isEmpty() ? 0 : stack.peek();
        while (!stack.isEmpty()) {
            int i = stack.pop();
            int left = i - (stack.isEmpty() ? 0 : (stack.peek() + 1));
            int right = edge - i;
            sum = (sum + (long)(left + right + left * right + 1) * arr[i]) % mod;
        }
        return (int)sum;
    }
}
