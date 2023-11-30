package org.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

//

/**
 * 完全二叉树

 * 宽度优先遍历
 * 1. 一个节点的左节点为空，右节点不为空 -- 一定不是
 * 2. 满足第一个条件，并且当出现左节点或右节点为空的情况，之后所有的节点都为叶子节点（左右节点都为空）
 */
public class CompleteBinaryTree {
    public static void main(String[] args) {

    }

    public boolean isCBT (Node head) {
        if (head == null) {return true;}
        Queue<Node> list = new LinkedList<>();
        list.add(head);
        boolean meetNull = false;
        while (!list.isEmpty()) {
            head = list.poll();
            if ((
                    (head.left != null || head.right != null) && meetNull)
                    ||
                    (head.left == null && head.right != null)
            ) {
                return false;
            }

            if(head.left != null) list.add(head.left);
            if (head.right != null) list.add(head.right);

            if(head.left == null || head.right == null) meetNull = true;

        }
        return true;
    }
}
