package org.algorithm.leetcode;

import java.util.*;

/**
 * 给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可能的句子。
 *
 * 注意：词典中的同一个单词可能在分段中被重复使用多次。
 *
 * 示例 1：
 *
 * 输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * 输出:["cats and dog","cat sand dog"]
 * 示例 2：
 *
 * 输入:s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * 输出:["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入:s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * 输出:[]
 *
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中所有字符串都 不同
 */
public class WordBreak {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a");
        new WordBreak().wordBreak("a", strings);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        WordTree wordTree = buildWordTree(wordDict);
        List<String> strings = wordBreak(s, wordTree);
        return strings == null ? new ArrayList<>() : strings;
    }

    static class WordTree {
        Map<Character, WordTreeNode> next;

        public WordTree() {
            this.next = new HashMap<>();
        }

        public void addString(String s) {
            char c = s.charAt(0);
            WordTreeNode node = next.getOrDefault(c, new WordTreeNode(c));
            node.addString(s, s.substring(1));
            next.put(c, node);
        }

        public WordTreeNode getNode(char c) {
            return next.get(c);
        }

    }
    static class WordTreeNode {
        // a-z
        char c;
        String word;
        Map<Character, WordTreeNode> next;

        public WordTreeNode(char c) {
            this.c = c;
            next = new HashMap<>();
        }

        public WordTreeNode getNode(char c) {
            return next.get(c);
        }

        public void addString(String s, String substring) {
            if (substring.length() == 0) {
                word = s;
                return;
            }
            char c = substring.charAt(0);
            WordTreeNode node = next.getOrDefault(c, new WordTreeNode(c));
            node.addString(s, substring.substring(1));
            next.put(c, node);
        }
    }


    private List<String> wordBreak(String s, WordTree wordTree) {
        return wordBreak(s.substring(1), wordTree, wordTree.getNode(s.charAt(0)));
    }

    private List<String> wordBreak(String s, WordTree wordTree, WordTreeNode root){
        if(root == null) {
            return null;
        }
        if (s.isEmpty()) {
            if (root.word != null) {
                return Arrays.asList(root.word);
            } else {
                return null;
            }
        }

        List<String> list = new ArrayList<>();
        if (root.word!= null) {
            List<String> backList = wordBreak(s, wordTree);
            if (backList == null || backList.isEmpty()) {

            } else {
                for (String backStr: backList) {
                    String res = root.word + " " + backStr;
                    list.add(res);
                }
            }

        }
        if (root.getNode(s.charAt(0)) != null) {
            List<String> strings = wordBreak(s.substring(1), wordTree, root.getNode(s.charAt(0)));
            if (strings != null) {
                list.addAll(strings);
            }

        }
        return list;
    }

    private WordTree buildWordTree(List<String> wordDict) {
        WordTree wordTree = new WordTree();
        for (String s : wordDict) {
            wordTree.addString(s);
        }
        return wordTree;
    }
}
