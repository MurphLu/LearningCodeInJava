package org.algorithm.tree;

/**
 * 搜索二叉树

 * 左节点最大值 < 当前节点 < 右节点最小值
 * 中序遍历 左 中 右，并且为满足条件
 */
public class BinarySearchTree {
    public static int preValue = Integer.MIN_VALUE;
    public static boolean isBST(Node node) {
        if (node == null) { return true; }
        boolean left = isBST(node.left);
        if(!left) { return false; }
        if(node.val <= preValue) {
            return false;
        } else {
            preValue = node.val;
        }
        return isBST(node.right);
    }

    static class ReturnType{
        boolean isBST;
        int min;
        int max;

        public ReturnType(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public static boolean isBST_(Node head){
        return process(head).isBST;
    }


    public static ReturnType process(Node head) {
        if (head == null) {
            return null;
        }
        ReturnType l = process(head.left);
        ReturnType r = process(head.right);
        int min = head.val;
        int max = head.val;
        if (l != null){
            min = Math.min(l.min, head.val);
            max = Math.max(l.max, head.val);
        }
        if (r != null){
            min = Math.min(r.min, head.val);
            max = Math.max(r.max, head.val);
        }

        boolean isBST = true;

        if(l!=null && (!l.isBST || (l.max >= head.val))) {
            isBST = false;
        }
        if(r!=null && (!r.isBST || (r.min <= head.val))) {
            isBST = false;
        }

        return new ReturnType(isBST, min,max);
    }



}
