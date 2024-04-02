package org.algorithm.leetcode.daily.tree;

import org.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeQue {

    /**
     * 给你一个整数 n ，请你找出所有可能含 n 个节点的 真二叉树 ，并以列表形式返回。答案中每棵树的每个节点都必须符合 Node.val == 0 。
     *
     * 答案的每个元素都是一棵真二叉树的根节点。你可以按 任意顺序 返回最终的真二叉树列表。
     *
     * 真二叉树 是一类二叉树，树中每个节点恰好有 0 或 2 个子节点。
     *
     * @param n 总结点个数，如果为偶数，那么必不能生成真二叉树
     * @return 所有真二叉树
     *
     * 保证根节点左侧奇数个节点，右侧也为奇数个节点，递归生成即可
     * 然后左侧生成的 list 和右侧生成的 list 嵌套遍历生成根节点，即得最终结果～～～
     */
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n % 2 == 0) return res;
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }

        int left = 1;
        int right = n - 1 - left;
        while (right >= 1) {
            List<TreeNode> leftNodes = allPossibleFBT(left);
            List<TreeNode> rightNodes = allPossibleFBT(right);
            for(TreeNode lNode: leftNodes) {
                for(TreeNode rNode : rightNodes) {
                    res.add(new TreeNode(0, lNode, rNode));
                }
            }
            left += 2;
            right -= 2;
        }
        return res;
    }
}
