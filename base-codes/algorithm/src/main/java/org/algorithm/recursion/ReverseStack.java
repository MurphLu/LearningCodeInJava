package org.algorithm.recursion;

import java.util.Stack;

/**
 * 不使用其他结构 stack 逆序
 */
public class ReverseStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        reverseStack(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }
    public static void reverseStack(Stack<Integer> s) {
        if (s.isEmpty()) {
            return;
        }
        int n = process(s);
        reverseStack(s);
        s.push(n);
    }


    public static int process(Stack<Integer> s) {
        int val = s.pop();
        if (s.isEmpty()) {
            return val;
        } else {
            int last = process(s);
            s.push(val);
            return last;
        }
    }
}
