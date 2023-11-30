package org.dp.core.composite;

import java.util.ArrayList;
import java.util.List;

// 组合模式，树状结构

public class Main {
    public static void main(String[] args) {
        LeafNode l1 = new LeafNode("aaa");
        LeafNode l2 = new LeafNode("aaa");
        LeafNode l3 = new LeafNode("aaa");
        LeafNode l4 = new LeafNode("aaa");
        Branch b1 = new Branch();
        Branch b2 = new Branch();
        Branch b3 = new Branch();
        b2.addNode(l1);
        b2.addNode(l2);
        b3.addNode(l3);
        b3.addNode(l4);
        b2.addNode(b3);
        b1.addNode(b2);
        b1.p();
    }
}

abstract class Node {
    abstract public void p();
}

class LeafNode extends Node {
    String content;

    public LeafNode(String content) {
        this.content = content;
    }

    @Override
    public void p() {
        System.out.println(content);
    }
}

class Branch extends Node {
    List<Node> nodeList = new ArrayList<>();

    public void addNode(Node n) {
        nodeList.add(n);
    }

    @Override
    public void p() {

        for (Node n: nodeList) {
            System.out.print("--");
            if(n instanceof LeafNode) {
                System.out.print(((LeafNode) n).content);
                System.out.println("");
            } else {
                n.p();
            }
        }
    }
}