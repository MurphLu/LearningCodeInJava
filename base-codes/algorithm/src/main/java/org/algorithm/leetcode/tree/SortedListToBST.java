package org.algorithm.leetcode.tree;

import org.algorithm.leetcode.ds.ListNode;
import org.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表的头节点 head ，其中的元素 按升序排序 ，将其转换为 平衡 二叉搜索树。
 */
public class SortedListToBST {
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return build(list, 0, list.size() - 1);
    }

    private TreeNode build(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return new TreeNode(list.get(left));
        }
        int mid = (right - left) / 2 + left;
        return new TreeNode(list.get(mid), build(list, left, mid - 1), build(list, mid+1, right));
    }
}
