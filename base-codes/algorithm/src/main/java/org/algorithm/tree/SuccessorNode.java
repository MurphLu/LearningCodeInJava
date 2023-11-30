package org.algorithm.tree;

// 后继结点，中序遍历中的下一个节点
public class SuccessorNode {
    static class Node {
        int val;
        Node left;
        Node right;
        Node parent;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

    }

    public static Node getSuccessor(Node node) {
        if (node == null) {
            return node;
        }
        // 如果当前节点的右节点有值，那么从右节点开始一直找到以右节点为根的子树的最左节点，为当前节点的后继
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else { // 没有右节点的话则一直向上找，直到找到的节点为父节点的左节点为止，返回父节点
            // 如果父节点为空，则该节点为该二叉树的最右节点
            Node parent = node.parent;
            while (parent != null && parent.left != node) {
                node = parent;
                parent = parent.parent;
            }
            return parent;
        }

    }

}
