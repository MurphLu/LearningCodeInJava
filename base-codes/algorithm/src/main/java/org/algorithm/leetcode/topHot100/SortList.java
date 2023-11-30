package org.algorithm.leetcode.topHot100;

public class SortList {
    public static void main(String[] args) {
        SortList s = new SortList();
        ListNode n = new ListNode(3, new ListNode(2, new ListNode(9, new ListNode(1))));
        ListNode node = s.sortList(n);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
    public ListNode sortList(ListNode head) {
        return splitAndMerge(head);
    }

    public ListNode splitAndMerge(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next.next;;
        ListNode slow = head;
        while (fast!= null) {
            slow = slow.next;
            fast = fast.next == null ? null : fast.next.next;
        }
        ListNode temp = slow.next;
        slow.next = null;
        ListNode split = splitAndMerge(head);
        ListNode split1 = splitAndMerge(temp);
        return merge(split, split1);
    }

    public ListNode merge(ListNode l1 ,ListNode l2) {
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
