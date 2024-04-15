package org.algorithm.leetcode.daily;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.NodeList;

import java.util.LinkedList;
import java.util.Objects;

/**
 * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 *
 * 实现 MyHashMap 类：
 *
 * MyHashMap() 用空映射初始化对象
 * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
 * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 *
 *
 * 0 <= key, value <= 10^6
 * 最多调用 104 次 put、get 和 remove 方法
 */

public class MyHashMap {


    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        String[] methods = new String[] {"put","put","put","put","remove","put","put","put","put","remove","put","put","put","put","put","put","put","put","get","put","put","put","put","put","put","put","put","remove","remove","put","put","get","put","put","get","get","get","put","get","get","put","remove","put","get","get","remove","put","put","put","get","put","get","put","put","put","get","put","remove","put","get","get","remove","put","put","remove","get","put","put","put","put","put","get","put","get","put","put","put","get","put","put","put","put","put","put","put","put","put","put","put","put","put","remove","get","put","put","put","put","put","get","remove"};
        int[][] arr = new int[][]{new int[]{54,35},new int[]{36,39},new int[]{63,9},new int[]{72,28},new int[]{78},new int[]{84,88},new int[]{56,42},new int[]{69,55},new int[]{4,47},new int[]{56},new int[]{24,46},new int[]{5,18},new int[]{35,94},new int[]{10,4},new int[]{50,67},new int[]{77,16},new int[]{75,48},new int[]{7,80},new int[]{65},new int[]{61,5},new int[]{52,32},new int[]{68,84},new int[]{54,18},new int[]{44,41},new int[]{17,60},new int[]{43,30},new int[]{30,49},new int[]{56},new int[]{54},new int[]{20,52},new int[]{4,0},new int[]{56},new int[]{98,51},new int[]{66,73},new int[]{23},new int[]{72},new int[]{83},new int[]{83,41},new int[]{13},new int[]{44},new int[]{91,93},new int[]{33},new int[]{16,53},new int[]{5},new int[]{77},new int[]{78},new int[]{80,40},new int[]{1,92},new int[]{93,35},new int[]{16},new int[]{86,60},new int[]{80},new int[]{94,91},new int[]{69,23},new int[]{4,72},new int[]{5},new int[]{53,15},new int[]{50},new int[]{82,14},new int[]{69},new int[]{35},new int[]{84},new int[]{82,44},new int[]{38,64},new int[]{4},new int[]{46},new int[]{20,16},new int[]{9,66},new int[]{15,64},new int[]{71,88},new int[]{82,11},new int[]{46},new int[]{37,29},new int[]{27},new int[]{80,78},new int[]{40,96},new int[]{21,60},new int[]{22},new int[]{12,1},new int[]{52,84},new int[]{44,56},new int[]{95,62},new int[]{16,67},new int[]{71,13},new int[]{93,17},new int[]{19,0},new int[]{61,13},new int[]{14,73},new int[]{29,46},new int[]{13,61},new int[]{2,12},new int[]{75},new int[]{60},new int[]{96,79},new int[]{45,88},new int[]{67,92},new int[]{86,75},new int[]{21,66},new int[]{91},new int[]{67}};
        for (int i = 0; i < methods.length; i++) {
            switch (methods[i]){
                case "put":
                    myHashMap.put(arr[i][0], arr[i][1]);
                    break;
                case "remove":
                    myHashMap.remove(arr[i][0]);
                    break;
                case "get":
                    System.out.println(myHashMap.get(arr[i][0]));
                    break;
                default:
                    continue;
            }
        }
        //},[],[],[],[],[],[],[],[89,95],[95],[30,31],[37,99],[51],[95,35],[65],[81],[61,46],[50,33],[59],[5],[75,89],[80,17],[35,94],[80],[19,68],[13,17],[70],[28,35],[99],[37],[13],[90,83],[41],[50],[29,98],[54,72],[6,8],[51,88],[13],[8,22],[85],[31,22],[60,9],[96],[6,35],[54],[15],[28],[51],[80,69],[58,92],[13,12],[91,56],[83,52],[8,48],[62],[54],[25],[36,4],[67,68],[83,36],[47,58],[82],[36],[30,85],[33,87],[42,18],[68,83],[50,53],[32,78],[48,90],[97,95],[13,8],[15,7],[5],[42],[20],[65],[57,9],[2,41],[6],[33],[16,44],[95,30]]
    }

    int mod = 8;
    LinkedList<Node>[] arr;

    int oldMod;
    LinkedList<Node>[] oldArr;

    static class Node {
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return key == node.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }


    public MyHashMap() {
        arr = new LinkedList[mod];
    }

    public void put(int key, int value) {
        int loc = getLoc(key);
        LinkedList<Node> list = arr[loc];
        if (list == null) {
            list = new LinkedList<>();
        }
        Node node = new Node(key, value);
        int index = list.indexOf(node);
        if (index == -1) {
            list.add(node);
        } else {
            list.set(index, node);
        }
        arr[loc] = list;
        if (arr[loc].size() > 8) {
            extend();
        }
    }

    public int get(int key) {
        int loc = getLoc(key);
        LinkedList<Node> list = arr[loc];
        if (list == null) {
            return -1;
        }
        Node node = new Node(key, 0);
        int index = list.indexOf(node);
        if(index == -1) {
            return -1;
        } else {
            return list.get(index).value;
        }
    }

    public void remove(int key) {
        int loc = getLoc(key);
        LinkedList<Node> list = arr[loc];
        list.remove(new Node(key, 0));
    }

    private void extend() {
        mod = mod << 1;
        oldArr = arr;
        arr = new LinkedList[mod];

        for(LinkedList<Node> linkedList : oldArr) {
            if (linkedList == null || linkedList.isEmpty()) {
            } else {
                for(Node node : linkedList) {
                    put(node.key, node.value);
                }
            }
        }
        oldArr = null;
    }

    private int getLoc(int key){
        return key % mod;
    }
}
