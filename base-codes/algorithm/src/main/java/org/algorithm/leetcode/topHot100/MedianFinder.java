package org.algorithm.leetcode.topHot100;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());

    }

    PriorityQueue<Integer> lowQueue = new PriorityQueue<>(((o1, o2) -> o2 - o1));
    PriorityQueue<Integer> highQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o));

    public MedianFinder() {

    }

    public void addNum(int num) {
        if (!lowQueue.isEmpty()) {
            int low = lowQueue.peek();
            if (num <= low) {
                lowQueue.add(num);
            } else {
                highQueue.add(num);
            }
            while (Math.abs(lowQueue.size() - highQueue.size()) > 1) {
                if (lowQueue.size() > highQueue.size()) {
                    highQueue.add(lowQueue.poll());
                } else {
                    lowQueue.add(highQueue.poll());
                }
            }
        } else {
            lowQueue.add(num);
        }

    }

    public double findMedian() {
        if (!lowQueue.isEmpty()) {
            if (lowQueue.size() != highQueue.size()) {
                return lowQueue.size() > highQueue.size() ? lowQueue.peek() : highQueue.peek();
            } else {
                return ((double)lowQueue.peek() + (double)highQueue.peek()) / 2.0;
            }
        } else {
            throw new NullPointerException("当前数据流为空");
        }
    }
}
