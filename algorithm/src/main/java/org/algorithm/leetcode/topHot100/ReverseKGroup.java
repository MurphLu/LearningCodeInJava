package org.algorithm.leetcode.topHot100;

import java.util.ArrayList;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

public class ReverseKGroup {
    public static void main(String[] args) {
        ReverseKGroup r = new ReverseKGroup();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode newHead = new ReverseKGroup().reverseKGroup(head, 3);
        while (newHead!= null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = null;
        ListNode newTail = null;
        while (true) {
            ListNode[] split = split(head, k);
            if (split.length == 2) {
                ListNode sp = split[0];
                ListNode[] reverse = reverse(sp);
                if (newHead == null) {
                    newHead = reverse[0];
                } else {
                    newTail.next = reverse[0];
                }
                newTail = reverse[1];

                head = split[1];
            } else {
                if (newHead == null) {
                    newHead = split[0];
                } else {
                    newTail.next = split[0];
                }
                break;
            }
        }
        return newHead;
    }

    private ListNode[] split(ListNode head, int k) {
        ListNode newHead = head;
        while (newHead != null && --k > 0) {
            newHead = newHead.next;
        }
        if(newHead != null) {
            ListNode temp = newHead.next;
            newHead.next = null;
            newHead = temp;
        }
        return k == 0 ? new ListNode[]{head, newHead} : new ListNode[]{head};
    }

    public ListNode[] reverse(ListNode head) {
        ListNode tail = head;
        ListNode newHead = head;
        head = head.next;
        newHead.next = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return new ListNode[]{newHead, tail};
    }
}
