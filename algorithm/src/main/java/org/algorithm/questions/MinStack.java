package org.algorithm.questions;

import java.util.LinkedList;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类:
 *
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 */
public class MinStack {

    public static void main(String[] args) {
        MinStack ms = new MinStack();
        ms.push(3);
        ms.push(6);
        ms.push(2);
        ms.push(1);
        System.out.println(ms.getMin());
        ms.pop();
        System.out.println(ms.getMin());
        System.out.println(ms.top());
    }

    Node head;

    static class Node {
        int val;
        int min;
        Node next;

        public Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }
    public MinStack() {

    }

    public void push(int val) {
        if (head == null) {
            head = new Node(val, val);
        }else {
            Node n = new Node(val, Math.min(val, head.min));
            n.next = head;
            head = n;
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
}
