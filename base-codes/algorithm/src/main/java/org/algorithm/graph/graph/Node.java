package org.algorithm.graph.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int val;
    public int in;
    int out;
    public List<Node> nexts;
    public List<Edge> edges;

    public Node(int val) {
        this.val = val;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
