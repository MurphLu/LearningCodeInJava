package org.algorithm.LinkList.questions;

import org.algorithm.LinkList.LNode;

import java.util.Objects;
import java.util.Stack;


// 是否回文
public class Palindrome {
    public static void main(String[] args) {
        LNode<Integer> lNode = new LNode<>(1,
                new LNode<>(2,
                        new LNode<>(3,
                                new LNode<>(3,
                                new LNode<>(2, new LNode<>(1, null))))));
        System.out.println(isPalindrome01(lNode));
        printNode(lNode);
    }
    // 额外空间复杂度为 O(N/2)
    private static boolean isPalindrome(LNode<Integer> lNode) {
        LNode<Integer> slow = lNode;
        LNode<Integer> fast = lNode;

        while (fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext() == null ? fast.getNext() : fast.getNext().getNext();
        }
        Stack<Integer> stack = new Stack<>();
        while (slow != null) {
            stack.push(slow.getVal());
            slow = slow.getNext();
        }
        while (!stack.empty()) {
            System.out.print(stack.pop());
        }
        LNode<Integer> t = lNode;
        while (!stack.empty()) {
            if(!Objects.equals(t.getVal(), stack.pop())) { return false; }
            t = t.getNext();
        }
        return true;
    }


    // 额外空间复杂度为 O(1)
    private static boolean isPalindrome01(LNode<Integer> lNode) {
        LNode<Integer> slow = lNode;
        LNode<Integer> fast = lNode;
        while (fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext() == null ? fast.getNext() : fast.getNext().getNext();
        }

        LNode<Integer> temp_slow = slow;
        LNode<Integer> temp_fast = fast;
        LNode<Integer> temp_curr = temp_slow.getNext();
        temp_slow.setNext(null);
        while (temp_curr != null) {
            LNode<Integer> temp = temp_curr;
            temp_curr = temp.getNext();
            temp.setNext(temp_slow);
            temp_slow = temp;
        }
        boolean result = true;
        while (lNode != slow) {
            System.out.println(lNode.getVal() + " " + temp_fast.getVal());
            System.out.println(Objects.equals(lNode.getVal(), temp_fast.getVal()));
            if(!Objects.equals(lNode.getVal(), temp_fast.getVal())){ result = false; }
            lNode = lNode.getNext();
            temp_fast = temp_fast.getNext();
        }

        LNode<Integer> r_head = fast;
        LNode<Integer> r_curr = r_head.getNext();
        r_head.setNext(null);

        while (r_curr != null) {
            LNode<Integer> temp = r_curr;
            r_curr = temp.getNext();
            temp.setNext(r_head);
            r_head = temp;
        }
        return result;
    }

    private static void printNode(LNode<Integer> lNode) {
        LNode<Integer> cur = lNode;
        int index = 0;
        while (cur!=null) {
            System.out.print(cur.getVal() + ", ");
            cur = cur.getNext();
        }
        System.out.println();
    }
}
