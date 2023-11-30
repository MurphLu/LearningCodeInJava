package org.algorithm.tree;

import java.util.List;

public class TireTree {
    public static class TireNode {
        int pass = 0; // 经过几次
        int end = 0; // 作为结尾次数
        TireNode[] nexts;

        public TireNode() {
            nexts = new TireNode[26];
        }
    }

    public static class Tire {
        private TireNode root;

        public Tire() {
            root = new TireNode();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }

            char[] chs = word.toCharArray();
            TireNode node = root;
            node.pass++;
            int index;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TireNode();
                }
                node = node.nexts[index];
                node.nexts[index].pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) == 0) {
                return;
            }
            TireNode node = root;
            node.pass--;
            char[] chs = word.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                TireNode tmp = node.nexts[index];
                if (--tmp.pass == 0) {
                    node.nexts[index] = null;
                    return;
                }
                node = tmp;
            }
            node.end--;
        }

        public int search(String word) {
            if (word == null) { return 0;}
            char[] chs = word.toCharArray();
            TireNode node = root;
            for (int i = 0; i < chs.length; i++) {
                node = node.nexts[chs[i] - 'a'];
                if (node == null) { return 0; }
            }
            return node.end;
        }

        public int prefix(String word) {
            if (word == null) {return 0;}
            char[] chs = word.toCharArray();
            TireNode node = root;
            for (int i = 0; i < chs.length; i++) {
                node = node.nexts[chs[i] - 'a'];
                if (node == null) { return 0; }
            }
            return node.pass;
        }
    }
}
