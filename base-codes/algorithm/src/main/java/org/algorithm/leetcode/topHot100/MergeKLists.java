package org.algorithm.leetcode.topHot100;

import org.algorithm.leetcode.ds.ListNode;

public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        int k = lists.length;
        if (k == 0) { return null; }
        ListNode l1 = lists[0];
        for (int i = 1; i < lists.length; i++) {
            l1 = mergeList(l1, lists[i]);
        }
        return l1;
    }

    public ListNode mergeList(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = null;
        ListNode tail = null;
        while (l1 != null && l2 != null) {
            if (head == null) {
                head = l1.val < l2.val ? l1 : l2;
                tail = head;
            } else {
                tail.next = l1.val < l2.val ? l1 : l2;
                tail = tail.next;
            }
            if (l1.val < l2.val) {
                l1 = l1.next;
            } else {
                l2 = l2.next;
            }
        }
        tail.next = l1 == null ? l2 : l1;
        return head;
    }
}
