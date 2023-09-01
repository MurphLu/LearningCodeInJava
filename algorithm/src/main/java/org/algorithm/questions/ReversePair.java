package org.algorithm.questions;

import org.algorithm.utils.ArrayUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;

// 一个数与比自己右侧小的数构成逆序对
public class ReversePair {
    private class Pair {
        int l;
        int r;

        public Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "l=" + l +
                    ", r=" + r +
                    '}';
        }
    }
    public static void main(String[] args) {
        int[] arr = {5,4,3,2,1};
        System.out.println(new ReversePair().process(arr, 0, arr.length - 1));
    }

    private int forP(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int times = 0;
            for (int j = i; j < arr.length; j++) {
                if(arr[i] < arr[j]) {
                    times += 1;
                }
            }
            sum += arr[i] * times;
        }
        return sum;
    }

    private ArrayList<Pair> process(int[] arr, int start, int end) {
        if (start == end) { return new ArrayList<Pair>(); }
        int mid = start + (end - start) / 2;
        ArrayList<Pair> l = process(arr, start, mid);
        ArrayList<Pair> r = process(arr, mid + 1, end);
        ArrayList<Pair> m = merge(arr, start, mid, end);
        m.addAll(l);
        m.addAll(r);
        return m;
    }

    private ArrayList<Pair> merge(int[] arr, int start, int mid, int end) {
        ArrayList<Pair> pairs = new ArrayList<>();

        int[] temp = new int[end - start + 1];
        int i = 0;
        int l = start;
        int r = mid + 1;
        while (i < temp.length) {
            while (l <= mid && r <= end) {
                int t;
                if(arr[l]<arr[r]) {
                    t = arr[l++];
                }else {
                    t = arr[r++];
                    for (int j = l; j <= mid; j++) {
                        pairs.add(new Pair(arr[j], t));
                    }
                }
                temp[i++] = t;
            }
            while(l <= mid) {
                temp[i++] = arr[l++];
            }
            while (r<=end) {
                temp[i++] = arr[r++];
            }
        }
        System.arraycopy(temp, 0, arr, start, temp.length);

        return pairs;
    }
}
