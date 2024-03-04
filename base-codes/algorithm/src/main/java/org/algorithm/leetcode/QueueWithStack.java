package org.algorithm.leetcode;

import java.util.Stack;

public class QueueWithStack {

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.pop());
        System.out.println(myQueue.peek());
    }
    static class MyQueue {
        Stack<Integer> in = new Stack<>();
        Stack<Integer> out = new Stack<>();

        int size;

        public MyQueue() {
            this.size = 0;
        }

        public void push(int x) {
            in.add(x);
            size ++;
        }

        public int pop() {
            if (out.empty()) {
                fillOut();
            }
            if (!out.isEmpty()) {
                size --;
            }
            return out.pop();
        }

        private void fillOut () {
            while (!in.isEmpty()) {
                out.add(in.pop());
            }
        }

        public int peek() {
            if (out.empty()) {
                fillOut();
            }
            return out.peek();
        }

        public boolean empty() {
            return size == 0;
        }
    }
}
