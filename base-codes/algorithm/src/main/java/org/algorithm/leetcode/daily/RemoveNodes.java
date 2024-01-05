package org.algorithm.leetcode.daily;

import org.algorithm.leetcode.ds.ListNode;

import java.util.Stack;

/**
 * 给你一个链表的头节点 head 。
 *
 * 移除每个右侧有一个更大数值的节点。
 *
 * 返回修改后链表的头节点 head 。
 */
public class RemoveNodes {
    public ListNode removeNodes(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        stack.add(head);
        ListNode curr = head.next;
        while (curr != null) {
            while (!stack.isEmpty() && stack.peek().val < curr.val){
                stack.pop();
            }
            stack.add(curr);
            curr = curr.next;
        }
        ListNode newHead = stack.pop();
        newHead.next = null;
        while (!stack.isEmpty()) {
            ListNode nextHead = stack.pop();
            nextHead.next = newHead;
            newHead = nextHead;
        }
        return newHead;
    }
}
