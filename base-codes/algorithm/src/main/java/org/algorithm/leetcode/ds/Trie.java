package org.algorithm.leetcode.ds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前缀树
 */
public class Trie {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("app"));
    }


    CharNode[] nodes;

    public Trie() {
        nodes = new CharNode[26];
    }

    public void insert(String word) {
        int ch = word.charAt(0)-'a';
        CharNode node = nodes[ch];
        if(node == null) {
            node = new CharNode();
        }
        node.insert(word.substring(1));
        nodes[ch] = node;
    }

    public boolean search(String word) {
        CharNode node = nodes[word.charAt(0)-'a'];
        if (node == null) {
            return false;
        }
        return node.search(word.substring(1));
    }

    public boolean startsWith(String word) {
        CharNode node = nodes[word.charAt(0)-'a'];
        if (node == null) {
            return false;
        }
        return node.startWithPrefix(word.substring(1));
    }

    static class CharNode {
        Map<Character, CharNode> children = new HashMap<>();

        boolean wordEnd;

        public CharNode() {}


        public void insert(String word) {
            if (word.length() == 0) {
                wordEnd = true;
                return;
            }
            char ch = word.charAt(0);
            CharNode node = children.getOrDefault(ch, new CharNode());
            node.insert(word.substring(1));
            children.put(ch, node);
            if (word.length() == 1) {
                node.wordEnd = true;
            }
        }

        public boolean search(String word) {
            if (word.length() == 0) {
                return wordEnd;
            }
            CharNode charNode = children.get(word.charAt(0));
            if(charNode == null){
                return false;
            }
            return charNode.search(word.substring(1));
        }

        public boolean startWithPrefix(String prefix) {
            if (prefix.length() == 0) {
                return true;
            }
            CharNode charNode = children.get(prefix.charAt(0));
            if(charNode == null){
                return false;
            }
            return charNode.startWithPrefix(prefix.substring(1));
        }
    }
}
