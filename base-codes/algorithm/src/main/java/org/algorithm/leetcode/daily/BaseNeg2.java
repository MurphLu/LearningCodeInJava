package org.algorithm.leetcode.daily;

import java.util.*;

public class BaseNeg2 {
    static class Info{
        int max;
        int min;
    }
    public static void main(String[] args) {

//        Map<Integer, Integer> keyMap = new HashMap<>();
//        for (int i = 0; i < 32; i++) {
//            keyMap.put(i, (int)Math.pow(-2, i));
//        }
//        System.out.println(keyMap);
//        Map<Integer, String> res = new HashMap<>();
//        res.put(0, "0"); //   0
//        res.put(1, "1"); //   1
//        res.put(2, "110"); // 4-2
//        res.put(3, "111"); // 4-2 + 1
//        res.put(4, "100"); // 4
//        res.put(5, "101"); // 5 + 1
//        res.put(6, "11010");// 16-8-2
//        res.put(7, "11011"); // 16-8-2 + 1
//        res.put(8, "11000");//
//        res.put(9, "11001");
//        res.put(12, "11100");
//        res.put(13, "11101");
//        res.put(14, "10010");
//        res.put(15, "10011");
//        res.put(16, "10000");
//
//        res.put(-1, "11");
//        res.put(-2, "10");
//        res.put(-3, "1101");
//        res.put(-4, "1100");
//        res.put(-5, "1111");
//        res.put(-6, "1110");
//        res.put(-7, "1001");
//        res.put(-8, "1000");
//        res.put(-9, "1011");
//        res.put(-10, "1010");

        System.out.println(new BaseNeg2().baseNeg2Imp(1652421));
    }

    /**
     * -2^4 + -2^3 + -2^1 + -2^0
     * 对 -2 取余是否为 0，不为 0 则说明最低为有值，设为 1，为 0 则直接设为 0 即可
     * 除 -2 则变为
     * -2^3 + -2^2 + -2^0 + 0
     * 继续次过程，知道 n 变为 0
     * @param n
     * @return
     */

    public String baseNeg2Final(int n) {
        if (n == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            // 计算余数
            int remainder = n % -2;
            n /= -2; // 如果余数为负数，需要调整
            if (remainder < 0) {
                remainder += 2; // 使余数变为正
                n += 1; // 调整商
            }
            sb.append(remainder);
        } // 反转字符串，因为添加时是从最低位开始的
        return sb.reverse().toString();

    }

    Map<Integer, String> map = new HashMap<>();
    Map<Integer, String> zeroStrMap = new HashMap<>();
    int pos = 1;
    int nag = 0;
    BaseNeg2() {
        map.put(0, "0");
        map.put(1, "1");
        for (int i = 0; i < 32; i++) {
            char[] arr = new char[i];
            Arrays.fill(arr, '0');
            zeroStrMap.put(i, new String(arr));
        }
    }


    public String baseNeg2(int n) {
        int i = 2;
        boolean isPos = false;
        while (i <= n ) {
            if (isPos) {
                int toPos = pos;
                int curLength = map.get(nag).length();
                for (int j = nag; j <= toPos && i <= n; j++) {
                    String s = map.get(j);
                    map.put(++pos, "1" + zeroStrMap.get(curLength - s.length()) + s);
                    i++;
                }
            } else {
                int toNag = nag;
                int curLength = map.get(pos).length();
                for (int j = pos; j >= toNag; j--) {
                    String s = map.get(j);
                    map.put(--nag, "1" + zeroStrMap.get(curLength - s.length()) + s);
                }
            }
            isPos = !isPos;
        }
        return map.get(n);
    }

    static class Node {
        Node pre;
        Node next;
        int val;
        String desc;

        public Node(int val, String desc) {
            this.val = val;
            this.desc = desc;
        }

        public Node(Node pre, int val, String desc, Node next) {
            this.pre = pre;
            this.next = next;
            this.val = val;
            this.desc = desc;
        }
    }

    static class NodeList {
        Node head;
        Node tail;

        public NodeList() {
            Node node = new Node(0, "0");
            head = node;
            tail = node;
        }

        public void addTail(Node node) {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }

        public void addHead(Node node) {
            head.pre = node;
            node.next = head;
            head = node;
        }

    }

    public String baseNeg2Imp(int n) {

        if (n == 0) {return "0";}
        if (n == 1) {return "1";}

        String[] zeroList = new String[32];
        for (int i = 0; i < 32; i++) {
            char[] cs = new char[i];
            Arrays.fill(cs, '0');
            zeroList[i] = new String(cs);
        }

        NodeList nodeList = new NodeList();
        nodeList.addTail(new Node(1, "1"));
        boolean addPos = false;
        while (true) {
            if (addPos) {
                Node node = nodeList.head;
                Node toNode = nodeList.tail;
                int val = toNode.val;
                int length = node.desc.length();
                while (node != toNode){
                    nodeList.addTail(
                            new Node(++val, "1" + zeroList[length - node.desc.length()] + node.desc)
                    );
                    if (val == n) {
                        return nodeList.tail.desc;
                    }
                    node = node.next;
                }
                nodeList.addTail(
                        new Node(++val, "1" + zeroList[length - node.desc.length()] + node.desc)
                );
                if (val == n) {
                    return nodeList.tail.desc;
                }
                addPos = !addPos;
            } else {
                Node node = nodeList.tail;
                Node toNode = nodeList.head;
                int val = toNode.val;
                int length = node.desc.length();
                while (node != toNode) {
                    nodeList.addHead(
                            new Node(--val, "1" + zeroList[length - node.desc.length()] + node.desc)
                    );
                    node = node.pre;
                }
                nodeList.addHead(
                        new Node(--val, "1" + zeroList[length - node.desc.length()] + node.desc)
                );
                addPos = !addPos;
            }
        }
    }

}
